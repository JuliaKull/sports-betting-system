package com.betpawa.betting.service.impl;

import com.betpawa.betting.entity.Bet;
import com.betpawa.betting.entity.Game;
import com.betpawa.betting.repository.BetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BettingServiceImplTest {

    @Mock
    BetRepository betRepository;

    @InjectMocks
    BettingServiceImpl bettingService;



    @Test
        public void shouldReturnAllBets() {
            List<Bet> bets = new ArrayList();
            bets.add(new Bet());

            given(betRepository.findAll()).willReturn(bets);

            List<Bet> expected = bettingService.getAll();

            assertEquals(expected, bets);
            verify(betRepository).findAll();
    }

    @Test
    void shouldReturnBetById() {
        Bet bet = Bet.builder()
                .id(1l)
                .gameId(new Game())
                .userId(1l)
                .status("WIN")
                .build();
        when(betRepository.findById(bet.getId())).thenReturn(Optional.of(bet));

        Bet expectedBet = betRepository.findById(bet.getId()).get();

        assertThat(expectedBet).isSameAs(bet);

        verify(betRepository).findById(bet.getId());
    }

    @Test
    void shouldThrowExceptionWhenBetDoesntExist() {
        Bet bet = Bet.builder()
                .id(1l)
                .gameId(new Game())
                .userId(1l)
                .status("WIN")
                .build();
        given(betRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));

        assertThrows(RuntimeException.class, () -> bettingService.findBetById(bet.getId()));
    }
}