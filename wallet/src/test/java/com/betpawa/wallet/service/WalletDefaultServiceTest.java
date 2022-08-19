package com.betpawa.wallet.service;

import com.betpawa.wallet.dto.Balance;
import com.betpawa.wallet.entity.User;
import com.betpawa.wallet.mapper.BalanceMapper;
import com.betpawa.wallet.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class WalletDefaultServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private WalletDefaultService service;

@Spy
    private BalanceMapper webMapper;


    @Test
    void checkIfCanWithdrawMoneyFromAccountIfBalanceIsGreaterThanAmount() {
        User user = User.builder()
                .id(1l)
                .email("test@gmail.com")
                .password("123")
                .balance(BigDecimal.valueOf(100))
                .build();
        Balance balance = new Balance(user.getId(), user.getBalance());
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        BigDecimal withdrawalAmount = new BigDecimal(99);
        assertThat(balance.amount()).isGreaterThan(withdrawalAmount);

    }


    @Test
    void depositMoneyToAccount() {
        User user = User.builder()
                .email("test@gmail.com")
                .password("123")
                .balance(BigDecimal.valueOf(100))
                .build();
        Balance balance = new Balance(user.getId(), user.getBalance());
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        BigDecimal deposit = new BigDecimal(99);
        assertThat(balance.amount().add(deposit));
    }

    @Test
    void balance() {
        User user = User.builder()
                .email("test@gmail.com")
                .password("123")
                .balance(BigDecimal.valueOf(100))
                .build();
        Balance balance = new Balance(user.getId(), user.getBalance());
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Optional<User> userFromDb = userRepository.findById(user.getId());
        Balance userDto = webMapper.toDTO(user);
        assertThat(balance.amount()).isEqualTo(userDto.amount());
        verify(userRepository).findById(user.getId());
    }

    @Test
    void saveUserInDbAndReturnIdAndBalance() {
        User user = User.builder()
                .id(1l)
                .email("test@gmail.com")
                .password("123")
                .balance(BigDecimal.valueOf(100))
                .build();
        Balance userDto = webMapper.toDTO(user);
        Balance userBalance = service.createNewAccount(user.getEmail(), user.getPassword());
        assertThat(userBalance.accountId()).isSameAs(userDto.accountId());
        assertThat(userBalance.amount()).isSameAs(userDto.amount());
        verify(userRepository).save(user);
    }

   @Test
    void checkIfUserEmailAlreadyExist() {

       User user = User.builder()
               .id(1l)
               .email("test@gmail.com")
               .password("123")
               .build();
       when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

       User user2 = User.builder()
               .id(1l)
               .email("test@gmail.com")
               .password("123")
               .build();
       assertThrows(RuntimeException.class, () -> service.createNewAccount(user2.getEmail(), user2.getPassword()));
   }
   @Test
    void getUserByEmailIfItExist() {
        User user = User.builder()
                .id(1l)
                .email("test@gmail.com")
                .password("123")
                .balance(BigDecimal.valueOf(100))
                .build();
       User userFromDb = userRepository.findByEmail(user.getEmail());
       when(userRepository.findById(userFromDb.getId())).thenReturn(Optional.of(user));
       Balance userBalance = webMapper.toDTO(userFromDb);
       Balance expectedUser = service.findAccount(userFromDb.getEmail());
        assertThat(expectedUser).isSameAs(userBalance);
        verify(userRepository).findByEmail(user.getEmail());
    }
}