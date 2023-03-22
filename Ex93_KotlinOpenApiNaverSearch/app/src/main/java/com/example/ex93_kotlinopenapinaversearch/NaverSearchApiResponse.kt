package com.example.ex93_kotlinopenapinaversearch


// 10_ Json 파싱 데이터를 저장할 data class 생성
data class NaverSearchApiResponse constructor(var total:Int,var display:Int,var items:MutableList<ShoppingItem>)

data class ShoppingItem(
    var title:String,
    var link:String,
    var image:String,
    // 11_ 읽어올 int 값이 없으면 에러날 수 있으므로 String 으로 받아오자.
    var lprice:String,
    var hprice:String,
    var mallName:String
    // 12_ 읽어올수 있는 정보는 더 있지만, 필요한것만 쏙쏙 빼오면 된다.
)