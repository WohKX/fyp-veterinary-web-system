package com.apufyp.wohkx.vetassociatesystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apufyp.wohkx.vetassociatesystem.constant.ChatbotTypeEnum;
import com.apufyp.wohkx.vetassociatesystem.model.Chatbot;
import com.apufyp.wohkx.vetassociatesystem.repository.ChatbotRepository;

@Service
public class ChatbotServices {

	@Autowired
	private ChatbotRepository chatbotRepository;

	private static final Logger logger = LoggerFactory.getLogger(ChatbotServices.class);

	public String deleteChatbotById(String id) {
		logger.info("Deleting Chatbot: " + id);
		chatbotRepository.deleteById(Long.parseLong(id));
		return "The chatbot has been deleted.";
	}

	public List<String> findClasses() {
		logger.info("Getting class type of chatbot.");
		List<String> classes = new ArrayList<String>();
		classes.addAll(chatbotRepository.findClassType());
		return classes;
	}

	public Chatbot findById(String id) {
		logger.info("Getting chatbot: " + id);
		return chatbotRepository.findById(Long.parseLong(id)).get();
	}

	public String updateChatbot(Chatbot chatbot) {
		logger.info("Updating chatbot: " + chatbot.getId());
		chatbotRepository.saveAndFlush(chatbot);
		return "The chatbot has been updated.";
	}

	public List<Chatbot> findAll() {
		logger.info("Getting all chatbot responses from database.");
		return chatbotRepository.findAll();
	}

	public String create(Chatbot chatbot) {
		logger.info("Creating new chatbot response: " + chatbot.getId());
		chatbotRepository.saveAndFlush(chatbot);
		return "The chatbot response have been created.";
	}

	public String getReply(String messageContext) {
		logger.info("Getting reply related to " + messageContext);
		String messageLowerCased = messageContext.toLowerCase();
		String nav = getNavigation(messageLowerCased);
		if (nav != "") {
			return nav;
		} else {
			int i = getKeywordCount(messageLowerCased);
			if (i > 0) {
				List<ChatbotTypeEnum> chatbotTypeEnums = new ArrayList<ChatbotTypeEnum>();
				chatbotTypeEnums.addAll(getKeywordEnum(messageLowerCased));
				if (i == 1) {
					return getResponse(chatbotTypeEnums.get(0).toString(), messageLowerCased);
				} else {
					int randomNum = ThreadLocalRandom.current().nextInt(i);
					ChatbotTypeEnum chatbotTypeEnum = chatbotTypeEnums.get(randomNum);
					return ("I preferred " + chatbotTypeEnum.toString() + " more, you can ask more precisely on it.");
				}

			} else {
				return getResponse(ChatbotTypeEnum.Others.toString(), messageLowerCased);
			}
		}
	}

	private String getResponse(String chatbotType, String messageLowerCased) {
		List<Chatbot> chatbots = getChatbot(chatbotType);
		List<Chatbot> possibleChatbots = new ArrayList<Chatbot>();
		List<String> possibleResponse = new ArrayList<String>();
		String response = "Sorry, I cannot answer the question, please raise an inquiry here.";
		for (Chatbot c : chatbots) {
			if (checkKeyword(c.getQuestions(), messageLowerCased)) {
				possibleChatbots.add(c);
			}
		}
		if (possibleChatbots.size() == 1) {
			response = possibleChatbots.get(0).getAnswers();
		} else if (possibleChatbots.size() > 1) {
			int randomNum = ThreadLocalRandom.current().nextInt(possibleChatbots.size());
			for (Chatbot c : possibleChatbots) {
				possibleResponse.add(c.getAnswers());
			}
			response = possibleResponse.get(randomNum);
		}
		return response;
	}

	private List<Chatbot> getChatbot(String chatbotType) {
		logger.info("Getting chatbots from database related to " + chatbotType);
		return chatbotRepository.findByClasses(chatbotType);
	}

	private List<ChatbotTypeEnum> getKeywordEnum(String messageLowerCased) {
		List<ChatbotTypeEnum> chatbotTypeEnums = new ArrayList<ChatbotTypeEnum>();
		if (checkKeyword("rabit", messageLowerCased) || checkKeyword("rabbit", messageLowerCased)
				|| checkKeyword("rabbits", messageLowerCased)) {
			chatbotTypeEnums.add(ChatbotTypeEnum.Rabbit);
		}
		if (checkKeyword("hamster", messageLowerCased) || checkKeyword("hamsters", messageLowerCased)) {
			chatbotTypeEnums.add(ChatbotTypeEnum.Hamster);
		}
		if (checkKeyword("cat", messageLowerCased) || checkKeyword("kitten", messageLowerCased)
				|| checkKeyword("kucing", messageLowerCased) || checkKeyword("kittens", messageLowerCased)
				|| checkKeyword("cats", messageLowerCased)) {
			chatbotTypeEnums.add(ChatbotTypeEnum.Cat);
		}
		if (checkKeyword("dog", messageLowerCased) || checkKeyword("anjing", messageLowerCased)
				|| checkKeyword("dogs", messageLowerCased)) {
			chatbotTypeEnums.add(ChatbotTypeEnum.Dog);
		}
		return chatbotTypeEnums;
	}

	private int getKeywordCount(String messageLowerCased) {
		int i = 0;
		if (checkKeyword("rabit", messageLowerCased) || checkKeyword("rabbit", messageLowerCased)
				|| checkKeyword("rabbits", messageLowerCased)) {
			i++;
		}
		if (checkKeyword("hamster", messageLowerCased) || checkKeyword("hamsters", messageLowerCased)) {
			i++;
		}
		if (checkKeyword("cat", messageLowerCased) || checkKeyword("kitten", messageLowerCased)
				|| checkKeyword("kucing", messageLowerCased) || checkKeyword("kittens", messageLowerCased)
				|| checkKeyword("cats", messageLowerCased)) {
			i++;
		}
		if (checkKeyword("dog", messageLowerCased) || checkKeyword("anjing", messageLowerCased)
				|| checkKeyword("dogs", messageLowerCased)) {
			i++;
		}
		return i;
	}

	private String getNavigation(String messageLowerCased) {
		if (checkKeyword("sick", messageLowerCased) || checkKeyword("sicked", messageLowerCased)
				|| checkKeyword("muntah", messageLowerCased) || checkKeyword("vomit", messageLowerCased)) {
			return "Communicate with Vet now!";
		} else if (checkKeyword("ticket", messageLowerCased) || checkKeyword("tiket", messageLowerCased)) {
			return ChatbotTypeEnum.Ticket.toString();
		} else if (checkKeyword("profile", messageLowerCased) || checkKeyword("password", messageLowerCased)
				|| checkKeyword("kata laluan", messageLowerCased)) {
			return ChatbotTypeEnum.Profile.toString();
		} else if (checkKeyword("vet", messageLowerCased) || checkKeyword("doctor", messageLowerCased)
				|| checkKeyword("doktor", messageLowerCased)) {
			return ChatbotTypeEnum.Veterinarian.toString();
		} else if (checkKeyword("clinic", messageLowerCased) || checkKeyword("hospital", messageLowerCased)
				|| checkKeyword("klinik", messageLowerCased)) {
			return ChatbotTypeEnum.ClinicHospital.toString();
		} else {
			return "";
		}
	}

	private boolean checkKeyword(String key, String input) {
		if (input.matches(".*\\b" + key + "\\b.*")) {
			return true;
		} else {
			return false;
		}

	}

	public void startup() {
		List<Chatbot> chatbots = chatbotRepository.findAll();
		if (chatbots.size() < 60) {
			startupbase();
			startupCat();
			startupDog();
			startupHamster();
			startupRabbit();
		}
	}

	private void startupbase() {
		List<Chatbot> chatbotList = new ArrayList<Chatbot>();
		Chatbot chatbot = new Chatbot(ChatbotTypeEnum.Others.toString(), "hi", "Hi, my dear what can I help you?");
		Chatbot chatbot2 = new Chatbot(ChatbotTypeEnum.Others.toString(), "halo", "Hi, whats can I help you?");
		Chatbot chatbot3 = new Chatbot(ChatbotTypeEnum.Others.toString(), "hello", "Hello, I am your assistant.");
		Chatbot chatbot4 = new Chatbot(ChatbotTypeEnum.Others.toString(), "morning", "Morning! May I help you?");
		Chatbot chatbot5 = new Chatbot(ChatbotTypeEnum.Others.toString(), "afternoon",
				"Good afternoon! What can I help you?");
		Chatbot chatbot6 = new Chatbot(ChatbotTypeEnum.Others.toString(), "night",
				"Yea, it's late already, wish you have a good dream.");
		Chatbot chatbot7 = new Chatbot(ChatbotTypeEnum.Others.toString(), "g9", "Good night dear.");
		Chatbot chatbot8 = new Chatbot(ChatbotTypeEnum.Others.toString(), "bye", "Bye, see you again.");
		Chatbot chatbot9 = new Chatbot(ChatbotTypeEnum.Others.toString(), "byebye", "Bye, see you again.");
		Chatbot chatbot10 = new Chatbot(ChatbotTypeEnum.Others.toString(), "what is the system for",
				"We are trying to provide a platform for pet owners to gain some "
						+ "basic knowledges on common pets. In addition, the application enables pet owner to book appointment fot their pets.");
		Chatbot chatbot11 = new Chatbot(ChatbotTypeEnum.Others.toString(), "function of system",
				"1. Book appointment for pet. <br>2. Chat with veterinarian for some "
						+ "information about pets. <br>3. Platform for pet owners to post questions or issue about pets.");
		Chatbot chatbot12 = new Chatbot(ChatbotTypeEnum.Others.toString(), "functions of system",
				"1. Book appointment for pet. <br>2. Chat with veterinarian for some "
						+ "information about pets. <br>3. Platform for pet owners to post questions or issue about pets.");
		chatbotList.add(chatbot);
		chatbotList.add(chatbot2);
		chatbotList.add(chatbot3);
		chatbotList.add(chatbot4);
		chatbotList.add(chatbot5);
		chatbotList.add(chatbot6);
		chatbotList.add(chatbot7);
		chatbotList.add(chatbot8);
		chatbotList.add(chatbot9);
		chatbotList.add(chatbot10);
		chatbotList.add(chatbot11);
		chatbotList.add(chatbot12);
		chatbotRepository.saveAllAndFlush(chatbotList);
	}

	private void startupCat() {
		logger.info("Creating chatbots for cat.");
		List<Chatbot> chatbotList = new ArrayList<Chatbot>();
		Chatbot chatbot = new Chatbot(ChatbotTypeEnum.Cat.toString(), "eat",
				"Common types of cat food are biscuits, canned food, fresh food, and raw food. In addition, if you choose biscuits as your main food, pair "
						+ "it with a wet food such as canned food or fresh food to help your cat get enough water.");
		Chatbot chatbot2 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "eats",
				"Common types of cat food are biscuits, canned food, fresh food, and raw food. In addition, if you choose biscuits as your main food, pair "
						+ "it with a wet food such as canned food or fresh food to help your cat get enough water.");
		Chatbot chatbot3 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "sick",
				"If your cat get sicked, please bring it to clinic or hospital for treatment, it is not suggested to feed it with human's medicine where the portion or ingredients might deadful for them.");
		Chatbot chatbot4 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "food",
				"Common types of cat food are biscuits, canned food, fresh food, and raw food. In addition, if you choose biscuits as your main food, pair "
						+ "it with a wet food such as canned food or fresh food to help your cat get enough water.");
		Chatbot chatbot5 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "foods",
				"Common types of cat food are biscuits, canned food, fresh food, and raw food. In addition, if you choose biscuits as your main food, pair "
						+ "it with a wet food such as canned food or fresh food to help your cat get enough water.");
		Chatbot chatbot6 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "drink",
				"Chronic kidney disease/renal failure is the leading cause of death in domestic cats, and the main cause is dehydration. Most metabolic waste "
						+ "cannot be eliminated in the urine if your cat does not drink enough water, resulting in elevated toxins in the body and kidney-related disorders.  <br>There are some ways recomended to increase their water intake: <br>"
						+ "1. Feed them with wet food. <br>2. Always provide fresh and clean water. <br>3. Provide flowing water(can buy some electrical appliances online) <br>4. Put more water containers for it in its activity area.");
		Chatbot chatbot7 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "drinks",
				"Chronic kidney disease/renal failure is the leading cause of death in domestic cats, and the main cause is dehydration. Most metabolic waste "
						+ "cannot be eliminated in the urine if your cat does not drink enough water, resulting in elevated toxins in the body and kidney-related disorders.  <br>There are some ways recomended to increase their water intake: <br>"
						+ "1. Feed them with wet food. <br>2. Always provide fresh and clean water. <br>3. Provide flowing water(can buy some electrical appliances online) <br>4. Put more water containers for it in its activity area.");
		Chatbot chatbot8 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "space",
				"1. Layer of space. Most felines are good at climbing, jumping, and climbing, so cats need a variety of living spaces in the home. Cats also like to occupy "
						+ "high points in the space, such as tables and chairs of different heights, cabinets or ceiling coves, etc. <br>2. Area planning. Don't put feed bowls, water bowls and litter boxes together.");
		Chatbot chatbot9 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "spaces",
				"1. Layer of space. Most felines are good at climbing, jumping, and climbing, so cats need a variety of living spaces in the home. Cats also like to occupy "
						+ "high points in the space, such as tables and chairs of different heights, cabinets or ceiling coves, etc. <br>2. Area planning. Don't put feed bowls, water bowls and litter boxes together.");
		Chatbot chatbot10 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "live",
				"1. Layer of space. Most felines are good at climbing, jumping, and climbing, so cats need a variety of living spaces in the home. Cats also like to occupy "
						+ "high points in the space, such as tables and chairs of different heights, cabinets or ceiling coves, etc. <br>2. Area planning. Don't put feed bowls, water bowls and litter boxes together.");
		Chatbot chatbot11 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "lives",
				"1. Layer of space. Most felines are good at climbing, jumping, and climbing, so cats need a variety of living spaces in the home. Cats also like to occupy "
						+ "high points in the space, such as tables and chairs of different heights, cabinets or ceiling coves, etc. <br>2. Area planning. Don't put feed bowls, water bowls and litter boxes together.");
		Chatbot chatbot12 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "intro",
				"Each cat has a distinct personality: they might be friendly, introverted, very energetic, or perpetually sluggish. You must be aware of your requirements and "
						+ "the type of cat you desire. Most foster homes nowadays will tell you about your cat's typical personality so you can pick the best one for you. Most kittens go through a phase of tremendous energy, while mature cats have a "
						+ "more consistent attitude, which can be considered.");
		Chatbot chatbot13 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "introduce",
				"Each cat has a distinct personality: they might be friendly, introverted, very energetic, or perpetually sluggish. You must be aware of your requirements and "
						+ "the type of cat you desire. Most foster homes nowadays will tell you about your cat's typical personality so you can pick the best one for you. Most kittens go through a phase of tremendous energy, while mature cats have a "
						+ "more consistent attitude, which can be considered.");
		Chatbot chatbot14 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "descrip",
				"Each cat has a distinct personality: they might be friendly, introverted, very energetic, or perpetually sluggish. You must be aware of your requirements and "
						+ "the type of cat you desire. Most foster homes nowadays will tell you about your cat's typical personality so you can pick the best one for you. Most kittens go through a phase of tremendous energy, while mature cats have a "
						+ "more consistent attitude, which can be considered.");
		Chatbot chatbot15 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "descript",
				"Each cat has a distinct personality: they might be friendly, introverted, very energetic, or perpetually sluggish. You must be aware of your requirements and "
						+ "the type of cat you desire. Most foster homes nowadays will tell you about your cat's typical personality so you can pick the best one for you. Most kittens go through a phase of tremendous energy, while mature cats have a "
						+ "more consistent attitude, which can be considered.");
		Chatbot chatbot16 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "description",
				"Each cat has a distinct personality: they might be friendly, introverted, very energetic, or perpetually sluggish. You must be aware of your requirements and "
						+ "the type of cat you desire. Most foster homes nowadays will tell you about your cat's typical personality so you can pick the best one for you. Most kittens go through a phase of tremendous energy, while mature cats have a "
						+ "more consistent attitude, which can be considered.");
		Chatbot chatbot17 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "newbie",
				"Remember to test your cat for feline distemper. Also, keep an eye out for ear mites. Condition of the skin. Ensure that there are no infectious diseases.");
		Chatbot chatbot18 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "new cat",
				"If it is your rirst cat please remember to test it for feline distemper. Also, keep an eye out for ear mites. Condition of the skin. Ensure that there are no "
						+ "infectious diseases. If it is the second one please prepare enough space for them, cats need an adaptation period for strangers.");
		Chatbot chatbot19 = new Chatbot(ChatbotTypeEnum.Cat.toString(), "tiro",
				"Remember to test your cat for feline distemper. Also, keep an eye out for ear mites. Condition of the skin. Ensure that there are no infectious diseases.");
		chatbotList.add(chatbot);
		chatbotList.add(chatbot2);
		chatbotList.add(chatbot3);
		chatbotList.add(chatbot4);
		chatbotList.add(chatbot5);
		chatbotList.add(chatbot6);
		chatbotList.add(chatbot7);
		chatbotList.add(chatbot8);
		chatbotList.add(chatbot9);
		chatbotList.add(chatbot10);
		chatbotList.add(chatbot11);
		chatbotList.add(chatbot12);
		chatbotList.add(chatbot13);
		chatbotList.add(chatbot14);
		chatbotList.add(chatbot15);
		chatbotList.add(chatbot16);
		chatbotList.add(chatbot17);
		chatbotList.add(chatbot18);
		chatbotList.add(chatbot19);
		chatbotRepository.saveAllAndFlush(chatbotList);

	}

	private void startupDog() {
		logger.info("Creating chatbots for dog.");
		List<Chatbot> chatbotList = new ArrayList<Chatbot>();
		Chatbot chatbot = new Chatbot(ChatbotTypeEnum.Dog.toString(), "eat",
				"1. Cooked meat. It is the favourite food of dogs, but do not give them frequently since caused them picky on foods. <br>2. Bones. Choose pigs, cattle or sheep bones, do "
						+ "not give them fine bones to avoid prickly the intestines. <br>3. Dog food(biscuits) as main foods. Normally dog food are based on their nutritional needs, but please pick the brands carefully.");
		Chatbot chatbot2 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "eats",
				"1. Cooked meat. It is the favourite food of dogs, but do not give them frequently since caused them picky on foods. <br>2. Bones. Choose pigs, cattle or sheep bones, do "
						+ "not give them fine bones to avoid prickly the intestines. <br>3. Dog food(biscuits) as main foods. Normally dog food are based on their nutritional needs, but please pick the brands carefully.");
		Chatbot chatbot3 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "sick",
				"If your dog get sicked, please bring it to clinic or hospital for treatment, it is not suggested to feed it with human's medicine where the portion or ingredients "
						+ "might deadful for them.");
		Chatbot chatbot4 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "food",
				"1. Cooked meat. It is the favourite food of dogs, but do not give them frequently since caused them picky on foods. <br>2. Bones. Choose pigs, cattle or sheep bones, do "
						+ "not give them fine bones to avoid prickly the intestines. <br>3. Dog food(biscuits) as main foods. Normally dog food are based on their nutritional needs, but please pick the brands carefully.");
		Chatbot chatbot5 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "foods",
				"1. Cooked meat. It is the favourite food of dogs, but do not give them frequently since caused them picky on foods. <br>2. Bones. Choose pigs, cattle or sheep bones, do "
						+ "not give them fine bones to avoid prickly the intestines. <br>3. Dog food(biscuits) as main foods. Normally dog food are based on their nutritional needs, but please pick the brands carefully.");
		Chatbot chatbot8 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "space",
				"It is not necessary to have a large space to keep a dog; as long as your dog has a spot to relax and enough room to get down or turn around freely, As with people, its "
						+ "living quarters do not need to be enormous, but it should be taken out on occasion, and it should be able to engage in spacious outdoor activities.");
		Chatbot chatbot9 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "spaces",
				"It is not necessary to have a large space to keep a dog; as long as your dog has a spot to relax and enough room to get down or turn around freely, As with people, its "
						+ "living quarters do not need to be enormous, but it should be taken out on occasion, and it should be able to engage in spacious outdoor activities.");
		Chatbot chatbot10 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "live",
				"It is not necessary to have a large space to keep a dog; as long as your dog has a spot to relax and enough room to get down or turn around freely, As with people, its "
						+ "living quarters do not need to be enormous, but it should be taken out on occasion, and it should be able to engage in spacious outdoor activities.");
		Chatbot chatbot11 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "lives",
				"It is not necessary to have a large space to keep a dog; as long as your dog has a spot to relax and enough room to get down or turn around freely, As with people, its "
						+ "living quarters do not need to be enormous, but it should be taken out on occasion, and it should be able to engage in spacious outdoor activities.");
		Chatbot chatbot12 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "intro",
				"Dog always referred as the best riend of human, where they can read their master's emotions. According to a survey on dogs, they will look forward to reuniting with "
						+ "their owners, at the same time are empathetic.");
		Chatbot chatbot13 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "introduce",
				"Dog always referred as the best riend of human, where they can read their master's emotions. According to a survey on dogs, they will look forward to reuniting with "
						+ "their owners, at the same time are empathetic.");
		Chatbot chatbot14 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "descrip",
				"Dog always referred as the best riend of human, where they can read their master's emotions. According to a survey on dogs, they will look forward to reuniting with "
						+ "their owners, at the same time are empathetic.");
		Chatbot chatbot15 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "descript",
				"Dog always referred as the best riend of human, where they can read their master's emotions. According to a survey on dogs, they will look forward to reuniting with "
						+ "their owners, at the same time are empathetic.");
		Chatbot chatbot16 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "description",
				"Dog always referred as the best riend of human, where they can read their master's emotions. According to a survey on dogs, they will look forward to reuniting with "
						+ "their owners, at the same time are empathetic.");
		Chatbot chatbot17 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "newbie",
				"Some important reminder for you about dog: <br>1. Do not bath your dog too often.(Weekly or once twice a month is acceptable) <br>2. Do not use human body wash to "
						+ "bath them. <br>3. Bring them out with a leash. <br>4. Do not feed dog with sweet treats.");
		Chatbot chatbot18 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "new dog",
				"Please ensure that you have time to accompany them, they also need companions from you dear.");
		Chatbot chatbot19 = new Chatbot(ChatbotTypeEnum.Dog.toString(), "tiro",
				"Some important reminder for you about dog: <br>1. Do not bath your dog too often.(Weekly or once twice a month is acceptable) <br>2. Do not use human body wash to "
						+ "bath them. <br>3. Bring them out with a leash. <br>4. Do not feed dog with sweet treats.");
		chatbotList.add(chatbot);
		chatbotList.add(chatbot2);
		chatbotList.add(chatbot3);
		chatbotList.add(chatbot4);
		chatbotList.add(chatbot5);
		chatbotList.add(chatbot8);
		chatbotList.add(chatbot9);
		chatbotList.add(chatbot10);
		chatbotList.add(chatbot11);
		chatbotList.add(chatbot12);
		chatbotList.add(chatbot13);
		chatbotList.add(chatbot14);
		chatbotList.add(chatbot15);
		chatbotList.add(chatbot16);
		chatbotList.add(chatbot17);
		chatbotList.add(chatbot18);
		chatbotList.add(chatbot19);
		chatbotRepository.saveAllAndFlush(chatbotList);

	}

	private void startupHamster() {
		logger.info("Creating chatbots for hamster.");
		List<Chatbot> chatbotList = new ArrayList<Chatbot>();
		Chatbot chatbot = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "eat",
				"Vegetables as main dishes: Asparagus, Broccoli, Chestbuts, Cucumbers etc. Meat/protein as side dishes: Mealworms, Grasshoppers, Crickets etc. Be aware of these foods: "
						+ "Almonds, Avocado, Eggplant, Chocolate, Candies, as well as human junk food.");
		Chatbot chatbot2 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "eats",
				"Vegetables as main dishes: Asparagus, Broccoli, Chestbuts, Cucumbers etc. Meat/protein as side dishes: Mealworms, Grasshoppers, Crickets etc. Be aware of these foods: "
						+ "Almonds, Avocado, Eggplant, Chocolate, Candies, as well as human junk food.");
		Chatbot chatbot3 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "sick",
				"If your hamster get sicked, please bring it to clinic or hospital for treatment, it is not suggested to feed it with human's medicine where the portion or ingredients "
						+ "might deadful for them.");
		Chatbot chatbot4 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "food",
				"Vegetables as main dishes: Asparagus, Broccoli, Chestbuts, Cucumbers etc. Meat/protein as side dishes: Mealworms, Grasshoppers, Crickets etc. Be aware of these foods: "
						+ "Almonds, Avocado, Eggplant, Chocolate, Candies, as well as human junk food.");
		Chatbot chatbot5 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "foods",
				"Vegetables as main dishes: Asparagus, Broccoli, Chestbuts, Cucumbers etc. Meat/protein as side dishes: Mealworms, Grasshoppers, Crickets etc. Be aware of these foods: "
						+ "Almonds, Avocado, Eggplant, Chocolate, Candies, as well as human junk food.");
		Chatbot chatbot8 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "space",
				"1. One cage for each hamster(The cage should be enough spaces for them, the cage size for small breed hamster should be at least 40x30x30(cm)) <br>2. Prepare hamster "
						+ "roller/wheel. <br>3. Rat sand and toilet sand(Rat sand is for them to clean themselves, cannot bath them with water)");
		Chatbot chatbot9 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "spaces",
				"1. One cage for each hamster(The cage should be enough spaces for them, the cage size for small breed hamster should be at least 40x30x30(cm)) <br>2. Prepare hamster "
						+ "roller/wheel. <br>3. Rat sand and toilet sand(Rat sand is for them to clean themselves, cannot bath them with water)");
		Chatbot chatbot10 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "live",
				"1. One cage for each hamster(The cage should be enough spaces for them, the cage size for small breed hamster should be at least 40x30x30(cm)) <br>2. Prepare hamster "
						+ "roller/wheel. <br>3. Rat sand and toilet sand(Rat sand is for them to clean themselves, cannot bath them with water)");
		Chatbot chatbot11 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "lives",
				"1. One cage for each hamster(The cage should be enough spaces for them, the cage size for small breed hamster should be at least 40x30x30(cm)) <br>2. Prepare hamster "
						+ "roller/wheel. <br>3. Rat sand and toilet sand(Rat sand is for them to clean themselves, cannot bath them with water)");
		Chatbot chatbot12 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "intro",
				"Hamster is the general name of hamster subfamily. There are 18 species in 7 genera, mainly in Asia and a few in Europe. Except for the small hamsters in Central Asia, "
						+ "all other species of hamsters have cheek pouches that can be used for temporary storage or to carry food back to their holes for storage, hence the name hamster.");
		Chatbot chatbot13 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "introduce",
				"Hamster is the general name of hamster subfamily. There are 18 species in 7 genera, mainly in Asia and a few in Europe. Except for the small hamsters in Central Asia, "
						+ "all other species of hamsters have cheek pouches that can be used for temporary storage or to carry food back to their holes for storage, hence the name hamster.");
		Chatbot chatbot14 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "descrip",
				"There are four common types of hamster could be found in the market: 1. Phodopus campbelli(Dwarf Campbells Russian Hamster) <br>2. Phodopus sungorus(Dwarf Winter White Russian Hamster) "
						+ "<br>3. Phodopus roborovskii(Roborovski Hamster) <br>4. Mesocricetus auratus(Syrian hamster) <br>Most of them could be breed in one cage, excepting the syrian hamster. However, it is not encouraged to do so because they might fight "
						+ "against each other, depends on their own temperament.");
		Chatbot chatbot15 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "descript",
				"There are four common types of hamster could be found in the market: 1. Phodopus campbelli(Dwarf Campbells Russian Hamster) <br>2. Phodopus sungorus(Dwarf Winter White Russian Hamster) "
						+ "<br>3. Phodopus roborovskii(Roborovski Hamster) <br>4. Mesocricetus auratus(Syrian hamster) <br>Most of them could be breed in one cage, excepting the syrian hamster. However, it is not encouraged to do so because they might fight "
						+ "against each other, depends on their own temperament.");
		Chatbot chatbot16 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "description",
				"There are four common types of hamster could be found in the market: 1. Phodopus campbelli(Dwarf Campbells Russian Hamster) <br>2. Phodopus sungorus(Dwarf Winter White Russian Hamster) "
						+ "<br>3. Phodopus roborovskii(Roborovski Hamster) <br>4. Mesocricetus auratus(Syrian hamster) <br>Most of them could be breed in one cage, excepting the syrian hamster. However, it is not encouraged to do so because they might fight "
						+ "against each other, depends on their own temperament.");
		Chatbot chatbot17 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "newbie",
				"Hamsters will only squeaks or chirps, but only when fighting, threatened, frightened or sick. Moreover, they are quite timid, please approach them slowly and do not "
						+ "catch them directly where they might bite you. Please remember to clean the rat sand and toilet sand at least once a week.");
		Chatbot chatbot18 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "new hamster",
				"If you breed a new hamster, please ensure that you prepare another cage for it, do not breed more than one hamster in one cage excepting theroborovski hamster "
						+ "where most of them are more tame than other species.");
		Chatbot chatbot19 = new Chatbot(ChatbotTypeEnum.Hamster.toString(), "tiro",
				"Hamsters will only squeaks or chirps, but only when fighting, threatened, frightened or sick. Moreover, they are quite timid, please approach them slowly and do not "
						+ "catch them directly where they might bite you. Please remember to clean the rat sand and toilet sand at least once a week.");
		chatbotList.add(chatbot);
		chatbotList.add(chatbot2);
		chatbotList.add(chatbot3);
		chatbotList.add(chatbot4);
		chatbotList.add(chatbot5);
		chatbotList.add(chatbot8);
		chatbotList.add(chatbot9);
		chatbotList.add(chatbot10);
		chatbotList.add(chatbot11);
		chatbotList.add(chatbot12);
		chatbotList.add(chatbot13);
		chatbotList.add(chatbot14);
		chatbotList.add(chatbot15);
		chatbotList.add(chatbot16);
		chatbotList.add(chatbot17);
		chatbotList.add(chatbot18);
		chatbotList.add(chatbot19);
		chatbotRepository.saveAllAndFlush(chatbotList);

	}

	private void startupRabbit() {
		logger.info("Creating chatbots for rabbit.");
		List<Chatbot> chatbotList = new ArrayList<Chatbot>();
		Chatbot chatbot = new Chatbot(ChatbotTypeEnum.Rabbit.toString(), "eat",
				"Main dish is forage! Do not feed them with carrot only where eating too many carrots can cause A-acidosis, especially in young rabbits. Moreover, do not give them spicy "
						+ "foods, please be remembered that they are herbivores.");
		Chatbot chatbot2 = new Chatbot(ChatbotTypeEnum.Rabbit.toString(), "eats",
				"Main dish is forage! Do not feed them with carrot only where eating too many carrots can cause A-acidosis, especially in young rabbits. Moreover, do not give them spicy "
						+ "foods, please be remembered that they are herbivores.");
		Chatbot chatbot3 = new Chatbot(ChatbotTypeEnum.Rabbit.toString(), "sick",
				"If your rabbit get sicked, please bring it to clinic or hospital for treatment, it is not suggested to feed it with human's medicine where the portion or ingredients might "
						+ "deadful for them.");
		Chatbot chatbot4 = new Chatbot(ChatbotTypeEnum.Rabbit.toString(), "food",
				"Main dish is forage! Do not feed them with carrot only where eating too many carrots can cause A-acidosis, especially in young rabbits. Moreover, do not give them spicy "
						+ "foods, please be remembered that they are herbivores.");
		Chatbot chatbot5 = new Chatbot(ChatbotTypeEnum.Rabbit.toString(), "foods",
				"Main dish is forage! Do not feed them with carrot only where eating too many carrots can cause A-acidosis, especially in young rabbits. Moreover, do not give them spicy "
						+ "foods, please be remembered that they are herbivores.");
		Chatbot chatbot12 = new Chatbot(ChatbotTypeEnum.Rabbit.toString(), "intro",
				"Rabbits have independent personality, but sometimes afraid of being alone, so be sure to take time out of your day to play with it. Moreover, they are afriad of water, "
						+ "where actuallly you do no need to bath them. As a pet owner of rabbit, yoou have to concern on their healthy where they do not bark like dogs and it is very difficult to found that they are falling into sick.");
		Chatbot chatbot13 = new Chatbot(ChatbotTypeEnum.Rabbit.toString(), "introduce",
				"Rabbits have independent personality, but sometimes afraid of being alone, so be sure to take time out of your day to play with it. Moreover, they are afriad of water, "
						+ "where actuallly you do no need to bath them. As a pet owner of rabbit, yoou have to concern on their healthy where they do not bark like dogs and it is very difficult to found that they are falling into sick.");
		Chatbot chatbot14 = new Chatbot(ChatbotTypeEnum.Rabbit.toString(), "descrip",
				"Rabbits have independent personality, but sometimes afraid of being alone, so be sure to take time out of your day to play with it. Moreover, they are afriad of water, "
						+ "where actuallly you do no need to bath them. As a pet owner of rabbit, yoou have to concern on their healthy where they do not bark like dogs and it is very difficult to found that they are falling into sick.");
		Chatbot chatbot15 = new Chatbot(ChatbotTypeEnum.Rabbit.toString(), "descript",
				"Rabbits have independent personality, but sometimes afraid of being alone, so be sure to take time out of your day to play with it. Moreover, they are afriad of water, "
						+ "where actuallly you do no need to bath them. As a pet owner of rabbit, yoou have to concern on their healthy where they do not bark like dogs and it is very difficult to found that they are falling into sick.");
		Chatbot chatbot16 = new Chatbot(ChatbotTypeEnum.Rabbit.toString(), "description",
				"Rabbits have independent personality, but sometimes afraid of being alone, so be sure to take time out of your day to play with it. Moreover, they are afriad of water, "
						+ "where actuallly you do no need to bath them. As a pet owner of rabbit, yoou have to concern on their healthy where they do not bark like dogs and it is very difficult to found that they are falling into sick.");
		Chatbot chatbot17 = new Chatbot(ChatbotTypeEnum.Rabbit.toString(), "newbie",
				"Female rabbits have the instinct to protect their young, so the territory is very strong, especially when you are not familiar with him, he may attack people. So do not "
						+ "forceful approach it when it is not familiar with you.");
		Chatbot chatbot18 = new Chatbot(ChatbotTypeEnum.Rabbit.toString(), "new rabbit",
				"Female rabbits have the instinct to protect their young, so the territory is very strong, especially when you are not familiar with him, he may attack people. So do not "
						+ "forceful approach it when it is not familiar with you.");
		Chatbot chatbot19 = new Chatbot(ChatbotTypeEnum.Rabbit.toString(), "tiro",
				"Female rabbits have the instinct to protect their young, so the territory is very strong, especially when you are not familiar with him, he may attack people. So do not "
						+ "forceful approach it when it is not familiar with you.");
		chatbotList.add(chatbot);
		chatbotList.add(chatbot2);
		chatbotList.add(chatbot3);
		chatbotList.add(chatbot4);
		chatbotList.add(chatbot5);
		chatbotList.add(chatbot12);
		chatbotList.add(chatbot13);
		chatbotList.add(chatbot14);
		chatbotList.add(chatbot15);
		chatbotList.add(chatbot16);
		chatbotList.add(chatbot17);
		chatbotList.add(chatbot18);
		chatbotList.add(chatbot19);
		chatbotRepository.saveAllAndFlush(chatbotList);

	}
}
