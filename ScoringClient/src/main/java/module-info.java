import dk.sdu.mmmi.cbse.ScoringClient;
import dk.sdu.mmmi.cbse.common.services.IScoreSystemService;

module ScoringClient {
    requires spring.web;
    requires spring.core;
    requires spring.beans;
    requires Common;
    provides IScoreSystemService with ScoringClient;
}