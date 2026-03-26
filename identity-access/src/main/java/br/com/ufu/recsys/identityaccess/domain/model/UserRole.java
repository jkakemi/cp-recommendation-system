package br.com.ufu.recsys.identityaccess.domain.model;

import java.util.UUID;

public class UserRole {
    private final UUID id;
    private final UUID userId;
    private final Role role;
    private final ContextType contextType;
    private final String contextId; // null se o contexto for global

    public UserRole(UUID id, UUID userId, Role role, ContextType contextType, String contextId) {
        if(contextType == ContextType.GLOBAL && contextId != null) {
            throw new IllegalArgumentException("Global context cannot have a specific contextId");
        }
        if(contextType != ContextType.GLOBAL && contextId == null) {
            throw new IllegalArgumentException("Specific context requires a contextId");
        }

        this.id = id;
        this.userId = userId;
        this.role = role;
        this.contextType = contextType;
        this.contextId = contextId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public Role getRole() {
        return role;
    }

    public ContextType getContextType() {
        return contextType;
    }

    public String getContextId() {
        return contextId;
    }
}
