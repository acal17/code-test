package integrations.turnitin.com.membersearcher.service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import integrations.turnitin.com.membersearcher.client.MembershipBackendClient;
import integrations.turnitin.com.membersearcher.model.MembershipList;
import integrations.turnitin.com.membersearcher.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipService {
	@Autowired
	private MembershipBackendClient membershipBackendClient;

	/**
	 * Method to fetch all memberships with their associated user details included.
	 * This method first calls out to the php-backend service and fetches all users
	 * and their ids, it then calls out to the backend service to fetch all memberships,
	 * matching each member to a user using the user id.
	 *
	 * @return A CompletableFuture containing a fully populated MembershipList object.
	 */
	public CompletableFuture<MembershipList> fetchAllMembershipsWithUsers() {
        CompletableFuture<Map<String, User>> userIdMap = membershipBackendClient.fetchUsers()
				.thenApply(users ->
						users.getUsers().stream()
								.collect(Collectors.toMap(User::getId, user -> user))
				);

		return membershipBackendClient.fetchMemberships()
				.thenCompose(members -> {
					CompletableFuture<?>[] userCalls = members.getMemberships().stream()
							.map(member -> userIdMap.thenApply(
									map -> member.setUser(map.get(member.getUserId())))
							)
							.toArray(CompletableFuture<?>[]::new);
					return CompletableFuture.allOf(userCalls)
							.thenApply(nil -> members);
				});
	}
}
