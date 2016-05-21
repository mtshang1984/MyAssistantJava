package LanguageArts;

public class LanguageArts {

	public LanguageArts() {
		// TODO Auto-generated constructor stub
	}
	public String replyToQuestion(String keyword){
		String reply="";
		switch (keyword){
		case "你好":
			reply="你好";
			break;
		default:
			break;
		}
		return reply;		
	}

	public String convince(String purpose){
		String talks="";
		switch (purpose){
		case "砍价":
			talks="这个价不卖，我就走了";
			talks+="我身上只带了这么多钱，回去就不一定再回来了。我们那里好多人需要，你给我便宜点，我给你推荐去。我不会给他们说这个价的";
			talks+="只要价钱合适，马上买";
			talks+="这里有点问题，你得便宜点";
			talks+="你这个（东西更好一些）也按照同样价格卖吧";
			
			break;
		case "吓唬":
			talks="你们在xx哪里的口碑都是怎么来的。";
		default:
			break;
		}
		return talks;		
	}
}
