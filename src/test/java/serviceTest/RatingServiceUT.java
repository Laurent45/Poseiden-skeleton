package serviceTest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exception.IdRequestUnknown;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.impl.RatingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RatingService.class)
public class RatingServiceUT {

    @Autowired
    private RatingService ratingServiceUT;

    @MockBean
    private RatingRepository ratingRepository;

    @Test
    public void whenSaveBidListThenReturnRatingList() {
        Rating rating = new Rating("moodysRating1"
                , "sandPRating1"
                , "fitchRating1"
                , 2);
        when(ratingRepository.save(rating)).thenReturn(rating);

        Assert.assertEquals(rating, ratingServiceUT.saveModel(rating));
    }

    @Test
    public void whenGetModelByIdThenReturnRatingList() throws IdRequestUnknown {
        Rating rating = new Rating("moodysRating1"
                , "sandPRating1"
                , "fitchRating1"
                , 2);
        rating.setId(1);
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        Assert.assertEquals(rating, ratingServiceUT.getModel(1));
    }

    @Test
    public void whenGetAllModelsThenReturnAllRating() {
        List<Rating> ratingList = Arrays.asList(new Rating("moodysRating1"
                , "sandPRating1"
                , "fitchRating1"
                , 2));
        when(ratingRepository.findAll()).thenReturn(ratingList);

        Assert.assertEquals(ratingList, ratingServiceUT.getAllModels());
    }
}
