package org.allGraphQLCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.allGraphQLCases.client.AnyCharacter;
import org.allGraphQLCases.client.Droid;
import org.allGraphQLCases.client.DroidInput;
import org.allGraphQLCases.client.Episode;
import org.allGraphQLCases.client.GraphQLRequest;
import org.allGraphQLCases.client.Human;
import org.allGraphQLCases.client.HumanInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.graphql_java_generator.client.GraphQLConfiguration;
import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

class UnionIT {

	HumanInput humanInput1;
	HumanInput humanInput2;
	DroidInput droidInput1;
	DroidInput droidInput2;
	Map<String, Object> params = new HashMap<>();

	@BeforeEach
	void setup() {
		// Default configuration for GraphQLRequest
		GraphQLRequest.setStaticConfiguration(new GraphQLConfiguration(Main.GRAPHQL_ENDPOINT));

		// A useful init for some tests
		humanInput1 = new HumanInput();
		humanInput1.setName("name human1");
		List<Episode> episodes = new ArrayList<>();
		episodes.add(Episode.JEDI);
		humanInput1.setAppearsIn(episodes);
		humanInput1.setHomePlanet("planet1");

		humanInput2 = new HumanInput();
		humanInput2.setName("name human2");
		episodes = new ArrayList<>();
		episodes.add(Episode.JEDI);
		episodes.add(Episode.EMPIRE);
		humanInput2.setAppearsIn(episodes);
		humanInput2.setHomePlanet("planet2");

		droidInput1 = new DroidInput();
		droidInput1.setName("name droid1");
		episodes = new ArrayList<>();
		episodes.add(Episode.NEWHOPE);
		droidInput1.setAppearsIn(episodes);
		droidInput1.setPrimaryFunction("primary function 1");

		droidInput2 = new DroidInput();
		droidInput2.setName("name droid2");
		episodes = new ArrayList<>();
		episodes.add(Episode.EMPIRE);
		episodes.add(Episode.NEWHOPE);
		droidInput2.setAppearsIn(episodes);
		droidInput2.setPrimaryFunction("primary function 2");

		params.put("human1", humanInput1);
		params.put("human2", humanInput2);
		params.put("droid1", droidInput1);
		params.put("droid2", droidInput2);
		params.put("uppercaseFalse", false);
		params.put("uppercaseTrue", true);
	}

	@Test
	void test_unionTest_withAFragmentForEachMember()
			throws GraphQLRequestPreparationException, GraphQLRequestExecutionException {
		// Preparation
		GraphQLRequest graphQLRequest = new GraphQLRequest(""//
				+ "query{unionTest(human1:?human1,droid1:?droid1,human2:?human2,droid2:?droid2) {" //
				+ "    ... on Character { ...id appearsIn } " //
				+ "    ... on Droid {  primaryFunction ... on Character {name(uppercase: ?uppercaseTrue) friends {name}}  } " //
				+ "    ... on Human {  homePlanet ... on Human { ... on Character  { name(uppercase: ?uppercaseTrue)}} } " //
				+ "  } "//
				+ "} " //
				+ "fragment id on Character {id} "//
		);

		// Go, go, go
		List<AnyCharacter> unionTest = graphQLRequest.execQuery(params).getUnionTest();

		// Verification
		assertNotNull(unionTest);
		assertTrue(unionTest.size() == 4);
		// humanInput1
		assertTrue(unionTest.get(0) instanceof Human);
		Human human1 = (Human) unionTest.get(0);
		assertNotNull(human1.getId());
		assertNotNull(human1.getAppearsIn());
		// assertEquals(1, human1.getAppearsIn().size()); The subobjects are randomly completed on server side. No
		// possible test there
		assertNull(human1.getFriends(), "no friends requested for humans");
		assertNotNull(human1.getHomePlanet());
		assertEquals("name human1", human1.getName(), "to uppercase field parameter set to true");
		// Human2
		assertTrue(unionTest.get(2) instanceof Human);
		Human human2 = (Human) unionTest.get(2);
		assertNotNull(human2.getId());
		assertNotNull(human2.getAppearsIn());
		// assertEquals(2, human2.getAppearsIn().size());The subobjects are randomly completed on server side. No
		// possible test there
		assertNull(human2.getFriends(), "no friends requested for humans");
		assertNotNull(human2.getHomePlanet());
		assertEquals("name human2", human2.getName(), "to uppercase field parameter set to true");
		// droidInput1
		assertTrue(unionTest.get(1) instanceof Droid);
		Droid droid1 = (Droid) unionTest.get(1);
		assertNotNull(droid1.getId());
		assertNotNull(droid1.getAppearsIn());
		// assertEquals(1, droid1.getAppearsIn().size());The subobjects are randomly completed on server side. No
		// possible test there
		assertNotNull(droid1.getFriends(), "friends are requested for humans");
		assertNotNull(droid1.getPrimaryFunction());
		assertEquals("name droid1", droid1.getName(), "to uppercase field parameter set to false");
		// droidInput2
		assertTrue(unionTest.get(3) instanceof Droid);
		Droid droid2 = (Droid) unionTest.get(3);
		assertNotNull(droid2.getId());
		assertNotNull(droid2.getAppearsIn());
		// assertEquals(2, droid2.getAppearsIn().size());The subobjects are randomly completed on server side. No
		// possible test there
		assertNotNull(droid2.getFriends(), "friends are requested for humans");
		assertNotNull(droid2.getPrimaryFunction());
		assertEquals("name droid2", droid2.getName(), "to uppercase field parameter set to false");
	}

	@Test
	void test_unionTest_withMissingFragments()
			throws GraphQLRequestPreparationException, GraphQLRequestExecutionException {
		// Preparation
		GraphQLRequest graphQLRequest_withoutFragmentForHuman = new GraphQLRequest(""//
				+ "query{unionTest(human1:?human1,droid1:?droid1,human2:?human2,droid2:?droid2) {" //
				+ "    ... on Droid { id primaryFunction ... on Character {name(uppercase: ?uppercaseTrue) friends {name}}  } " //
				+ "  } "//
				+ "} " //
		);

		// Go, go, go
		List<AnyCharacter> unionTest = graphQLRequest_withoutFragmentForHuman.execQuery(params).getUnionTest();

		// Verification
		assertNotNull(unionTest);
		assertTrue(unionTest.size() == 4);
		//
		// humanInput1 ==> Only the__typename is filled (for proper JSON deserialization)
		assertTrue(unionTest.get(0) instanceof Human);
		Human human1 = (Human) unionTest.get(0);
		assertNull(human1.getId());
		assertNull(human1.getAppearsIn());
		assertNull(human1.getFriends());
		assertNull(human1.getHomePlanet());
		assertNull(human1.getName());
		//
		// Human2
		assertTrue(unionTest.get(2) instanceof Human);
		Human human2 = (Human) unionTest.get(2);
		assertNull(human2.getId());
		assertNull(human2.getAppearsIn());
		assertNull(human2.getFriends(), "no friends requested for humans");
		assertNull(human2.getHomePlanet());
		assertNull(human2.getName(), "to uppercase field parameter set to true");
		//
		// droidInput1
		assertTrue(unionTest.get(1) instanceof Droid);
		Droid droid1 = (Droid) unionTest.get(1);
		assertNotNull(droid1.getId());
		assertNull(droid1.getAppearsIn());
		assertNotNull(droid1.getFriends(), "friends are requested for humans");
		assertNotNull(droid1.getPrimaryFunction());
		assertEquals("name droid1", droid1.getName(), "to uppercase field parameter set to false");
		//
		// droidInput2
		assertTrue(unionTest.get(3) instanceof Droid);
		Droid droid2 = (Droid) unionTest.get(3);
		assertNotNull(droid2.getId());
		assertNull(droid2.getAppearsIn());
		assertNotNull(droid2.getFriends(), "friends are requested for humans");
		assertNotNull(droid2.getPrimaryFunction());
		assertEquals("name droid2", droid2.getName(), "to uppercase field parameter set to false");
	}
}
