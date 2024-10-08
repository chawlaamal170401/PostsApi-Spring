package org.amal.postapispring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

  private List<Post> postList;
  private PostService postService1;
  private PostService postService2;


  public PostController(@Qualifier("fakePostService") PostService postService1, @Qualifier("anotherPostService") PostService postService2){
    this.postService1 = postService1;
    this.postService2 = postService2;
    postList = new ArrayList<>();
    postList.add(new Post(1, "Post 1", "Post Body 1", 1));
    postList.add(new Post(2, "Post 2", "Post Body 2", 1));
  }

  @GetMapping("")
  public ResponseEntity<List<Post>> getAllPost(){
    System.out.println("PostService: " + postService2.doSomething());
    return ResponseEntity.status(HttpStatus.OK).body(postList);
  }

  @PostMapping("")
  public ResponseEntity<Post> createPost(@RequestBody Post post){
    postList.add(post);
    return ResponseEntity.status(HttpStatus.CREATED).body(post);
  }

  @GetMapping("/{postId}")
  public ResponseEntity<?> getPostById(@PathVariable int postId){
    for(Post post: postList){
      if(post.getId() == postId){
        return ResponseEntity.status(HttpStatus.OK).body(post);
      }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<?> deletePostById(@PathVariable int postId){
    for(Post post: postList){
      if(post.getId() == postId){
        postList.remove(post);
        return ResponseEntity.status(HttpStatus.OK).body(post);
      }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
  }


  @PatchMapping("/{postId}")
  public ResponseEntity<?> updatePostById(@PathVariable int postId, @RequestBody Post updatedPost){
    for(Post post: postList){
      if(post.getId() == postId){
        postList.remove(post);
        postList.add(updatedPost);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
      }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
  }

}
