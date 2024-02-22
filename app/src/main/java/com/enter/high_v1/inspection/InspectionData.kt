package com.enter.high_v1.inspection

data class InspectionQuestListData(
    val question: String,
    val answer01: String,
    val answer02: String,
    val answer03: String,
    val answer04: String,
    val answer05: String,
    val answer06: String,
    val answer07: String,
    val answer08: String,
    val answer09: String,
    val answer10: String,
    val answerScore01: String,
    val answerScore02: String,
    val answerScore03: String,
    val answerScore04: String,
    val answerScore05: String,
    val answerScore06: String,
    val answerScore07: String,
    val answerScore08: String,
    val answerScore09: String,
    val answerScore10: String,
    val tip1Score: String,
    val tip2Score: String,
    val tip3Score: String,
    val tip1Desc: String,
    val tip2Desc: String,
    val tip3Desc: String,
    val qitemNo: Int
)

data class InspectionQuestion(
    val SUCC_YN: String,
    val ERROR_REASON: String,
    val RESULT: List<InspectionQuestListData>
)

data class InspectionResultRequest(
    val apikey: String,
    val qestrnSeq: String,
    val trgetSe: String,
    val name: String,
    val gender: String,
    val school: String,
    val grade: String,
    val email: String,
    val startDtm: String,
    val answer: String
)

data class InspectionResultData(
    val inspctSeq: Int,
    val url: String
)

data class InspectionResult(
    val SUCC_YN: String,
    val ERROR_REASON: String,
    val RESULT: InspectionResultData
)

data class InspectionCrawling(
    val num1: String
)