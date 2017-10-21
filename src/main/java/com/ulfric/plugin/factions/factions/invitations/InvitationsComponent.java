package com.ulfric.plugin.factions.factions.invitations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections4.MapUtils;

import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;

public class InvitationsComponent extends Component {
	
	public static final ComponentKey<InvitationsComponent> KEY = InvitationsComponentKey.INSTANCE;

	private Map<UUID, Invitation> invitations;

	public Map<UUID, Invitation> getInvitations() {
		return invitations;
	}

	public void setInvitations(Map<UUID, Invitation> invitations) {
		this.invitations = invitations;
	}

	public Invitation getInvitation(UUID user) {
		Map<UUID, Invitation> invitations = getInvitations();

		if (invitations == null) {
			return null;
		}

		return invitations.get(user);
	}

	public List<Invitation> getSortedInvitations() {
		Map<UUID, Invitation> invitations = getInvitations();
		if (MapUtils.isEmpty(invitations)) {
			return Collections.emptyList();
		}
		List<Invitation> sorted = new ArrayList<>(invitations.values());
		Collections.sort(sorted);
		return sorted;
	}

}
