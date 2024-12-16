package com.keyin.binarytreevisalizerbackend;

import com.keyin.binarytreevisalizerbackend.controller.GraphController;
import com.keyin.binarytreevisalizerbackend.entity.Graph;
import com.keyin.binarytreevisalizerbackend.repository.GraphRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GraphControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GraphRepository graphRepository;

    @InjectMocks
    private GraphController graphController;

    private Graph graph;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(graphController).build();
        graph = new Graph();
    }

    // Test for getting all graphs
    @Test
    void testGetAllGraphs() throws Exception {
        // Arrange
        when(graphRepository.findAll()).thenReturn(List.of(graph));

        // Act & Assert
        mockMvc.perform(get("/graphs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Graph"));

        verify(graphRepository, times(1)).findAll();
    }

    // Test for creating a new graph
    @Test
    void testCreateGraph() throws Exception {
        // Arrange
        when(graphRepository.save(any(Graph.class))).thenReturn(graph);

        // Act & Assert
        mockMvc.perform(post("/graphs")
                        .contentType("application/json")
                        .content("{\"id\":\"1\", \"title\":\"Test Graph\", \"date\":\"2024-12-15\", \"content\":\"Some content\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Graph"));

        verify(graphRepository, times(1)).save(any(Graph.class));
    }

    // Test for updating an existing graph
    @Test
    void testUpdateGraph() throws Exception {
        // Arrange
        when(graphRepository.findById("1")).thenReturn(Optional.of(graph));
        when(graphRepository.save(any(Graph.class))).thenReturn(graph);

        // Act & Assert
        mockMvc.perform(put("/graphs/1")
                        .contentType("application/json")
                        .content("{\"title\":\"Updated Graph\", \"date\":\"2024-12-15\", \"content\":\"Updated content\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Graph"));

        verify(graphRepository, times(1)).findById("1");
        verify(graphRepository, times(1)).save(any(Graph.class));
    }

    // Test for deleting a graph
    @Test
    void testDeleteGraph() throws Exception {
        // Arrange
        when(graphRepository.existsById("1")).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/graphs/1"))
                .andExpect(status().isNoContent());

        verify(graphRepository, times(1)).existsById("1");
        verify(graphRepository, times(1)).deleteById("1");
    }

    @Test
    void testDeleteGraphNotFound() throws Exception {
        // Arrange
        when(graphRepository.existsById("1")).thenReturn(false);

        // Act & Assert
        mockMvc.perform(delete("/graphs/1"))
                .andExpect(status().isNotFound());

        verify(graphRepository, times(1)).existsById("1");
    }
}
