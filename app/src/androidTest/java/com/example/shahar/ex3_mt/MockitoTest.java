package com.example.shahar.ex3_mt;
/**
 * Created by noysi on 14-Jan-18.
 */


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    private static final String Email = "Noy@gmail.com";
    @Mock
    User user;

    @Test
    public void EmailTest() {
        user = mock(User.class);
        when(user.getEmail()).thenReturn(Email);
        assertEquals(user.getEmail(), Email);
    }

}