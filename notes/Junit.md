# JUnit

### various assets method
- assertEquals
- assertNotEquals
- assertTrue
- assertFalse
- assertNull
- assertNotnull
- assertArrayEqual
---
- @BeforeEach - Method annotated with this annotation is invoked before every test case execution
- @AfterEach - Method annotated with this annotation is invoked after every test case execution
- @Before
- @After
---
- @Mock - Its mocks a particular class or interface
- @InjectMocks - It injects all the class/interface annoated with @Mock into the class which is annotated with @InjectMocks
---
we can return multiple values from the mocked function
```java
public class ListTest {
    
    @Test
    public void testSize_multipleReturns() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(10).thenReturn(20);
        assertEquals(10, listMock.size());
        assertEquals(20, listMock.size());
        assertEquals(20, listMock.size());
        assertEquals(20, listMock.size());
        assertEquals(20, listMock.size());
    }
}
```
