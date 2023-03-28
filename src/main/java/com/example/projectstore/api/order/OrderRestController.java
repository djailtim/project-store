//package com.example.projectstore.api.order;
//
//import com.example.projectstore.api.dto.UserDTO;
//import com.example.projectstore.api.repositories.ProductDBRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//@RestController
//@RequestMapping("/users/{userId}/order")
//@RequiredArgsConstructor
//@Log4j2
//public class OrderRestController {
//
//  private final ProductDBRepository productDBRepository;
//
//    @PostMapping("/{movieId}")
//    public ResponseEntity<FavDTO> salvar(@PathVariable Long userId, @PathVariable Long movieId) {
//        UserModel userModel = getUserModel(userId);
//        MovieModel movieModel = this.movieJpaRespository.findById(movieId).orElseThrow();
//        Optional<FavModel> favModelOptional = favJpaRepository.findByUserModelIdAndMovieModelId(userModel.getId(), movieModel.getId());
//        if (favModelOptional.isPresent()) {
//            return new ResponseEntity<>(this.convertToFavDTO(favModelOptional.get()), HttpStatus.OK);
//        }
//        FavModel favModel = this.favJpaRepository.save(new FavModel(movieModel, userModel));
//        return new ResponseEntity<>(this.convertToFavDTO(favModel), HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/{movieId}")
//    public ResponseEntity<FavDTO> remover(@PathVariable Long userId, @PathVariable Long movieId) {
//        UserModel userModel = getUserModel(userId);
//        MovieModel movieModel = this.movieJpaRespository.findById(movieId).orElseThrow();
//        Optional<FavModel> optionalFavModel = this.favJpaRepository.findByUserModelIdAndMovieModelId(userModel.getId(),
//                movieModel.getId());
//        if (optionalFavModel.isPresent()) {
//            this.favJpaRepository.delete(optionalFavModel.get());
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    private FavDTO convertToFavDTO(FavModel favModel) {
//        return new FavDTO(new UserDTO(favModel.getUserModel()), new MovieDTO(favModel.getMovieModel()));
//    }
//
//    private UserModel getUserModel(Long userId) {
//        return this.userJpaRepository.findById(userId).orElseThrow();
//    }
//
//    @GetMapping
//    public List<FavDTO> listardoUsuario(@PathVariable Long userId) {
//        UserModel userModel = this.getUserModel(userId);
//        return this.favJpaRepository.findByUserModelId(userModel.getId());
//    }
//
//    @GetMapping("/projection")
//    public List<FavBasicDTO> listarProjectiondoUsuario(@PathVariable Long userId) {
//        UserModel userModel = this.getUserModel(userId);
//        return this.favJpaRepository.findBasicByUserModelId(userModel.getId());
//    }
//
//
//}
//
