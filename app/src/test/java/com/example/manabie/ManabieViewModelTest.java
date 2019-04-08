package com.example.manabie;

import android.app.Application;

import com.example.manabie.resources.ManabieRepository;
import com.example.manabie.resources.model.ManabieItem;
import com.example.manabie.view.ManabieViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class ManabieViewModelTest {
    private ManabieViewModel manabieViewModel;
    private ManabieRepository manabieRepository;

    @Mock
    Application application;

    @Before
    public void setupManabieViewModel() {
        MockitoAnnotations.initMocks(this);

        manabieViewModel = new ManabieViewModel(application);
    }


    @Test
    public void listManabieNotNullTest() {
        List<ManabieItem> manabieItemList = new ArrayList<>();

        
    }
}
