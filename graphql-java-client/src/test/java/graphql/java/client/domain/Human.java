package graphql.java.client.domain;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author generated by graphql-maven-plugin
 */
public class Human implements Character {

	String id;
	String name;

	@JsonDeserialize(as = CharacterImpl.class)
	Character bestFriend;

	@JsonDeserialize(contentAs = CharacterImpl.class)
	List<Character> friends;

	@JsonDeserialize(contentAs = Episode.class)
	List<Episode> appearsIn;

	String homePlanet;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setBestFriend(Character bestFriend) {
		this.bestFriend = bestFriend;
	}

	public Character getBestFriend() {
		return bestFriend;
	}

	public void setFriends(List<Character> friends) {
		this.friends = friends;
	}

	public List<Character> getFriends() {
		return friends;
	}

	public void setAppearsIn(List<Episode> appearsIn) {
		this.appearsIn = appearsIn;
	}

	public List<Episode> getAppearsIn() {
		return appearsIn;
	}

	public void setHomePlanet(String homePlanet) {
		this.homePlanet = homePlanet;
	}

	public String getHomePlanet() {
		return homePlanet;
	}

	public String toString() {
		return "Human {" + "id: " + id + ", " + "name: " + name + ", " + "bestFriend: " + bestFriend + ", "
				+ "friends: " + friends + ", " + "appearsIn: " + appearsIn + ", " + "homePlanet: " + homePlanet + "}";
	}
}
