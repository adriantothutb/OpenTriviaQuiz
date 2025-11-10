package com.opentriviaquiz.data.remote // balík: data vrstva, vzdialené DTO modely

// ---------- KATEGÓRIE ----------
data class CategoryListDto( // koreňová odpoveď pre zoznam kategórií
    val trivia_categories: List<CategoryDto> // pole kategórií pod kľúčom "trivia_categories"
)

data class CategoryDto( // jedna kategória
    val id: Int,       // jedinečné ID kategórie
    val name: String   // názov kategórie (môže obsahovať HTML entity)
)

// ---------- OTÁZKY ----------
data class QuestionsResponseDto( // koreňová odpoveď pre otázky
    val response_code: Int,      // 0 = OK, iné hodnoty = rôzne stavy (napr. vyčerpané otázky)
    val results: List<QuestionDto> // pole otázok
)

data class QuestionDto(  // jedna otázka
    val category: String,           // názov kategórie (text)
    val type: String,               // "multiple" alebo "boolean"
    val difficulty: String,         // "easy", "medium", "hard"
    val question: String,           // samotná otázka (často s HTML entitami)
    val correct_answer: String,     // správna odpoveď (string)
    val incorrect_answers: List<String> // nesprávne odpovede (zoznam)
)

