package com.betpawa.betting.service.impl;

import com.betpawa.betting.entity.Game;
import com.betpawa.betting.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
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
class GameServiceImplTest {

    @Mock
    GameRepository repository;

    @InjectMocks
    GameServiceImpl service;

    @Test
    public void shouldReturnAllGames() {
        List<Game> games = new ArrayList();
        games.add(new Game());

        given(repository.findAll()).willReturn(games);

        List<Game> expected = service.getAll();

        assertEquals(expected, games);
        verify(repository).findAll();
    }

    @Test
    void shouldReturnBetById() {
        Game game = Game.builder()
                .id(1l)
                .city("London")
                .date(LocalDateTime.now())
                .teamOne("Team1")
                .oodsTeamOne(2)
                .teamTwo("Team2")
                .oodsTeamOne(3)
                .build();
        when(repository.findById(game.getId())).thenReturn(Optional.of(game));

        Game expected = repository.findById(game.getId()).get();

        assertThat(expected).isSameAs(game);

        verify(repository).findById(game.getId());
    }

    @Test
    void shouldThrowExceptionWhenBetDoesntExist() {
        Game game = Game.builder()
                .id(1l)
                .city("London")
                .date(LocalDateTime.now())
                .teamOne("Team1")
                .oodsTeamOne(2)
                .teamTwo("Team2")
                .oodsTeamOne(3)
                .build();
        given(repository.findById(anyLong())).willReturn(Optional.ofNullable(null));

        assertThrows(RuntimeException.class, () -> service.findGameById(game.getId()));
    }

}