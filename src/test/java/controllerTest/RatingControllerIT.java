package controllerTest;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.impl.RatingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RatingController.class)
public class RatingControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private RatingService ratingService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc =
                MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenHomeThenReturnViewRatingAndAllRating() throws Exception {
        Rating rating = new Rating("moodysRating1"
                , "sandPRating1"
                , "fitchRating1"
                , 2);
        List<Rating> ratings = Arrays.asList(rating);
        when(ratingService.getAllModels()).thenReturn(ratings);

        mockMvc.perform(get("/rating/list"))
                .andExpect(view().name("rating/list"))
                .andExpect(model().attributeExists("allRatings"))
                .andReturn();
    }

    @Test
    public void whenAddRatingFormThenReturnViewRatingAdd() throws Exception {
        mockMvc.perform(get("/rating/add"))
                .andExpect(view().name("rating/add"))
                .andExpect(model().attributeExists("rating"))
                .andReturn();
    }
    @Test
    public void whenValidateThenReturnRating() throws Exception {
        Rating rating = new Rating("moodysRating1"
                , "sandPRating1"
                , "fitchRating1"
                , 2);
        when(ratingService.saveModel(rating)).thenReturn(rating);
        mockMvc.perform(post("/rating/validate")
                        .param("moodysRating", "moodysRating1")
                        .param("sandPRating", "sandPRating1")
                        .param("fitchRating", "fitchRating1")
                        .param("orderNumber", "2"))
                .andExpect(view().name("redirect:/rating/list"));
        verify(ratingService).saveModel(rating);
    }

    @Test
    public void whenGetShowUpdateFormThenReturnFormAndModel() throws Exception {
        Rating rating = new Rating("moodysRating1"
                , "sandPRating1"
                , "fitchRating1"
                , 2);
        when(ratingService.getModel(1)).thenReturn(rating);

        mockMvc.perform(get("/rating/update/{id}", 1))
                .andExpect(view().name("rating/update"))
                .andExpect(model().attributeExists("rating"))
                .andExpect(view().name("rating/update"));
        verify(ratingService).getModel(1);
    }

    @Test
    public void whenPostUpdateBidThenReturnRating() throws Exception {
        Rating rating = new Rating("moodysRating1"
                , "sandPRating1"
                , "fitchRating1"
                , 2);
        rating.setId(1);

        mockMvc.perform(post("/rating/update/{id}", "1")
                        .param("moodysRating", "moodysRating1")
                        .param("sandPRating", "sandPRating1")
                        .param("fitchRating", "fitchRating1")
                        .param("orderNumber", "2"))
                .andExpect(view().name("redirect:/rating/list"));
        verify(ratingService).updateModel(1, rating);
    }

    @Test
    public void whenGetDeleteBidThenReturnRating() throws Exception {
        mockMvc.perform(get("/rating/delete/{id}", "1"))
                .andExpect(view().name("redirect:/rating/list"));
        verify(ratingService).deleteModel(1);
    }
}