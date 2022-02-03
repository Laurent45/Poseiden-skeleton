package serviceTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exception.IdRequestUnknown;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.impl.CurvePointService;
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
@SpringBootTest(classes = CurvePointService.class)
public class CurveServiceUT {

    @Autowired
    private CurvePointService curveServiceUT;

    @MockBean
    private CurvePointRepository curvePointRepository;

    @Test
    public void whenSaveBidListThenReturnCurvePointList() {
        CurvePoint curvePoint = new CurvePoint(1
                , 10D
                , 5D);
        when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);

        Assert.assertEquals(curvePoint, curveServiceUT.saveModel(curvePoint));
    }

    @Test
    public void whenGetModelByIdThenReturnCurvePointList() throws IdRequestUnknown {
        CurvePoint curvePoint = new CurvePoint(1
                , 10D
                , 5D);
        curvePoint.setId(1);
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));

        Assert.assertEquals(curvePoint, curveServiceUT.getModel(1));
    }

    @Test
    public void whenGetAllModelsThenReturnAllBidList() {
        List<CurvePoint> curvePointList = Arrays.asList(new CurvePoint(1
                , 10D
                , 5D));
        when(curvePointRepository.findAll()).thenReturn(curvePointList);

        Assert.assertEquals(curvePointList, curveServiceUT.getAllModels());
    }
}
