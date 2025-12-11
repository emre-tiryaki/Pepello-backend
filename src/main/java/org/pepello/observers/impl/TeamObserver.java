package org.pepello.observers.impl;

import jakarta.annotation.PostConstruct;
import org.pepello.dto.events.TeamCreatedEvent;
import org.pepello.observers.ITeamObserver;
import org.pepello.service.impl.TeamServiceImpl;
import org.pepello.service.impl.UserTeamRelationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamObserver implements ITeamObserver {
    @Autowired
    private UserTeamRelationServiceImpl userTeamRelationService;
    @Autowired
    private TeamServiceImpl teamService;

    @PostConstruct
    public void registerObserver(){
        System.out.println("TeamObserver kayıt edildi");
        teamService.addObserver(this);
    }

    @Override
    public void onTeamCreated(TeamCreatedEvent event) {
        System.out.println("Observer ile takım ilişkisi kuruluyor");

        userTeamRelationService.addRelation(
                event.user().getId(),
                event.team().getId()
        );
    }
}
