package com.crud.tasks.mapper;


import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void testMapToBoards() {

        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "First name", true);
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(trelloListDto);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("Test Id", "Test Name", trelloLists);
        List <TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add((trelloBoardDto));

        //When
        List <TrelloBoard> mappedBoard = trelloMapper.mapToBoards(trelloBoards);

        //Then
        assertEquals(1, mappedBoard.size());
    }

    @Test
    void testMapToBoardsDto() {
        //Given
        List <TrelloList> trelloLists = new ArrayList<>();
        TrelloBoard trelloBoard = new TrelloBoard("Test Id", "Test Name", trelloLists);
        List <TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

        //When
        List <TrelloBoardDto> mappedBoard = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(1, mappedBoard.size());
    }

    @Test
    void testMapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("Test Id", "Test Name", true);
        List <TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto);

        //When
        List <TrelloList> mappedList = trelloMapper.mapToList(trelloListDtos);

        //Then
        assertEquals(1, mappedList.size());
    }

    @Test
    void testMapToListDto() {
        //Given
        TrelloList trelloList = new TrelloList("Test id", "Test Name", true);
        List <TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        //When
        List <TrelloListDto> mappedList = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(1, mappedList.size());
    }

    @Test
    void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Test id", "test description", "Center", "001");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("test description", trelloCardDto.getDescription());
    }

    @Test
    void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test id", "test description", "Center", "001");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("Center", trelloCard.getPos());
    }
}
