package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.dto.FileDto;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.model.*;
import ru.job4j.cars.service.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static ru.job4j.cars.timezone.TimeZoneConverter.convert;

@Controller
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final ModelService carModelService;
    private final TypeService typeService;
    private final EngineService engineService;
    private final CarService carService;
    private final FileService fileService;

    @GetMapping("/list")
    public String getAllPosts(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("posts", convert(user, postService.findAll()));
        return "posts/list";
    }

    @GetMapping("/last-day")
    public String getNewPosts(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("posts", convert(user, postService.findPostsByToday()));
        return "posts/last-day";
    }

    @GetMapping("/with-photo")
    public String getPostsWithPhoto(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("posts", convert(user, postService.findAllWithPhotos()));
        return "posts/with-photo";
    }

    @GetMapping("/done")
    public String getDonePosts(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("posts", convert(user, postService.findAllWithPhotos()));
        return "posts/done";
    }

    @GetMapping("/actual")
    public String getActualPosts(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("posts", convert(user, postService.findAllWithPhotos()));
        return "posts/actual";
    }

    @GetMapping("/create")
    public String initAddForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("posts", convert(user, postService.findAll()));
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("carModels", carModelService.findAll());
        model.addAttribute("engines", engineService.findAll());
        return "posts/create";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute PostDto postForm,
                         @RequestParam MultipartFile multipartFile, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Post post = getPostByPostForm(postForm);
        post.setUser(user);
        if (multipartFile.getSize() > 0) {
            setFileToPost(multipartFile, post);
        }
        if (postService.save(post) == null) {
            model.addAttribute("user", user);
            model.addAttribute("message", "Ошибка создания поста");
            return "error/error";
        }
        return "redirect:/posts/list";
    }


    private void setFileToPost(MultipartFile multipartFile, Post post) {
        Optional<File> fileOptional = getFileOptionalFromMultipartFile(multipartFile);
        if (fileOptional.isPresent()) {
            File file = fileOptional.get();
            post.getFileList().add(file);
        }
    }

    private Optional<File> getFileOptionalFromMultipartFile(MultipartFile multipartFile) {
        Optional<File> fileOptional;
        try {
            FileDto fileDto =
                    new FileDto(multipartFile.getOriginalFilename(), multipartFile.getBytes());
            fileOptional = Optional.of(fileService.save(fileDto));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileOptional;
    }

    private Post getPostByPostForm(PostDto postForm) {
        Optional<Category> categoryOptional = categoryService.findById(postForm.getCategoryId());
        Optional<CarModel> carModelOptional = carModelService.findById(postForm.getCarModelId());
        Optional<Type> typeOptional = typeService.findById(postForm.getTypeId());
        Optional<Engine> engineOptional = engineService.findById(postForm.getEngineId());
        Car car = new Car();
        if (categoryOptional.isPresent() && carModelOptional.isPresent()
                && typeOptional.isPresent() && engineOptional.isPresent()) {
            car.setCategory(categoryOptional.get());
            car.setModel(carModelOptional.get());
            car.setCarType(typeOptional.get());
            car.setEngine(engineOptional.get());
        }
        Post post = new Post();
        post.setFileList(new ArrayList<>());
        Optional<Car> carOptional = carService.save(car);
        car = carOptional.orElseThrow();
        post.setCar(car);
        post.setDescription(postForm.getDescription());
        return post;
    }
}