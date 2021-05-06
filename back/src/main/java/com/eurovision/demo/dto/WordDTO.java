package com.eurovision.demo.dto;

import com.eurovision.demo.domain.Word;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordDTO {

    private long id;
    private String name;

    public WordDTO(WordDTOBuilder wordDTOBuilder) {

        setId(wordDTOBuilder.word.getId());
        setName(wordDTOBuilder.word.getName());
    }


    public static WordDTOBuilder builder() {
        return new WordDTOBuilder();
    }


    public static final class WordDTOBuilder{

        private Word word;

        private WordDTOBuilder() {
        }

        public WordDTOBuilder withWord(Word word) {
            this.word = word;
            return this;
        }

        public WordDTO build() {
            return new WordDTO(this);
        }

    }
}
