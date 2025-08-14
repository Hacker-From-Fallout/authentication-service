package com.ignat.chernyshov.auth.Services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ignat.chernyshov.auth.repositories.CustomerUserRepository;
import com.ignat.chernyshov.auth.services.DefaultCustomerUserService;

@ExtendWith(MockitoExtension.class)
public class CustomerUserServiceImplTest {

    @Mock
    private CustomerUserRepository customerUserRepository;

    @InjectMocks
    private DefaultCustomerUserService customerUserService;

    // @Test
    // void testFindAll_ReturnsListOfCustomerUsers() {
    //     // подготовка данных
    //     CustomerUser user1 = new CustomerUser();
    //     user1.setId(1L);
    //     CustomerUser user2 = new CustomerUser();
    //     user2.setId(2L);
    //     List<CustomerUser> mockList = Arrays.asList(user1, user2);

    //     // настройка моков
    //     when(customerUserRepository.findAll()).thenReturn(mockList);

    //     // вызов метода
    //     List<CustomerUser> result = customerUserService.findAll();

    //     // проверки
    //     assertNotNull(result);
    //     assertEquals(2, result.size());
    //     assertEquals(user1, result.get(0));
    //     assertEquals(user2, result.get(1));

    //     verify(customerUserRepository).findAll(); // убедиться, что вызван метод репозитория
    // }
}
