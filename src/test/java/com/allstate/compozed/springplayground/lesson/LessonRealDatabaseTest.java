package com.allstate.compozed.springplayground.lesson;

/**
 * Created by localadmin on 7/19/17.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class LessonRealDatabaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LessonRepository repository;

    @Transactional
    @Rollback
    @Test
    public void createGeneratesANewLesson() throws Exception {
        assertEquals(((Collection<LessonModel>)repository.findAll()).size(), 0);

        mockMvc.perform(post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Mock me another one!\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Mock me another one!")));

        assertEquals(((Collection<LessonModel>)repository.findAll()).size(), 1);
    }

//    @Transactional
//    @Rollback
//    @Test
//    public void updateModifiesExistingRecord() {
//        LessonModel lesson = new LessonModel();
//        lesson.setTitle("Old Title");
//        repository.save(lesson);
//
//        mockMvc.perform(patch("/lessons/" + lesson.getId()))
//
//    }
}
