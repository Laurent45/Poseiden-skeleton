package controllerTest;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.impl.RuleNameService;
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
@SpringBootTest(classes = RuleNameController.class)
public class RuleNameControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private RuleNameService ruleNameService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc =
                MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenHomeThenReturnViewRuleNameAndAllRuleName() throws Exception {
        RuleName ruleName = new RuleName("name1"
                , "description1"
                , "json1"
                , "template1"
                , "sqlStr1"
                , "sqlPart1");
        List<RuleName> ruleNames = Arrays.asList(ruleName);
        when(ruleNameService.getAllModels()).thenReturn(ruleNames);

        mockMvc.perform(get("/ruleName/list"))
                .andExpect(view().name("ruleName/list"))
                .andExpect(model().attributeExists("allRuleName"))
                .andReturn();
    }

    @Test
    public void whenAddRuleNameFormThenReturnViewRuleNameAdd() throws Exception {
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(view().name("ruleName/add"))
                .andExpect(model().attributeExists("ruleName"))
                .andReturn();
    }

    @Test
    public void whenValidateThenReturnRuleName() throws Exception {
        RuleName ruleName = new RuleName("name1"
                , "description1"
                , "json1"
                , "template1"
                , "sqlStr1"
                , "sqlPart1");
        when(ruleNameService.saveModel(ruleName)).thenReturn(ruleName);

        mockMvc.perform(post("/ruleName/validate")
                        .param("name", "name1")
                        .param("description", "description1")
                        .param("json", "json1")
                        .param("template", "template1")
                        .param("sqlStr", "sqlStr1")
                        .param("sqlPart", "sqlPart1"))
                .andExpect(view().name("redirect:/ruleName/list"));
        verify(ruleNameService).saveModel(ruleName);
    }

    @Test
    public void whenGetShowUpdateFormThenReturnFormAndModel() throws Exception {
        RuleName ruleName = new RuleName("name1"
                , "description1"
                , "json1"
                , "template1"
                , "sqlStr1"
                , "sqlPart1");
        when(ruleNameService.getModel(1)).thenReturn(ruleName);

        mockMvc.perform(get("/ruleName/update/{id}", 1))
                .andExpect(view().name("ruleName/update"))
                .andExpect(model().attributeExists("ruleName"))
                .andExpect(view().name("ruleName/update"));
        verify(ruleNameService).getModel(1);
    }

    @Test
    public void whenPostUpdateBidThenReturnRuleName() throws Exception {
        RuleName ruleName = new RuleName("name1"
                , "description1"
                , "json1"
                , "template1"
                , "sqlStr1"
                , "sqlPart1");
        ruleName.setId(1);

        mockMvc.perform(post("/ruleName/update/{id}", "1")
                        .param("name", "name1")
                        .param("description", "description1")
                        .param("json", "json1")
                        .param("template", "template1")
                        .param("sqlStr", "sqlStr1")
                        .param("sqlPart", "sqlPart1"))
                .andExpect(view().name("redirect:/ruleName/list"));
        verify(ruleNameService).updateModel(1, ruleName);
    }

    @Test
    public void whenGetDeleteBidThenReturnRuleName() throws Exception {
        mockMvc.perform(get("/ruleName/delete/{id}", "1"))
                .andExpect(view().name("redirect:/ruleName/list"));
        verify(ruleNameService).deleteModel(1);
    }
}