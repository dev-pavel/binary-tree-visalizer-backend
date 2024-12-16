package com.keyin.binarytreevisalizerbackend.controller;

import com.keyin.binarytreevisalizerbackend.entity.Graph;
import com.keyin.binarytreevisalizerbackend.repository.GraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/graphs")
public class GraphController {

    @Autowired
    private GraphRepository graphRepository;

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Graph> getAllGraphs() {
        return graphRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public Graph createGraph(@RequestBody Graph graph) {
        return graphRepository.save(graph);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<Graph> updateGraph(@PathVariable String id, @RequestBody Graph graphDetails) {
        Optional<Graph> optionalGraph = graphRepository.findById(id);
        if (optionalGraph.isPresent()) {
            Graph graph = optionalGraph.get();
            graph.setTitle(graphDetails.getTitle());
            graph.setDate(graphDetails.getDate());
            graph.setContent(graphDetails.getContent());
            return ResponseEntity.ok(graphRepository.save(graph));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGraph(@PathVariable String id) {
        if (graphRepository.existsById(id)) {
            graphRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}