package hex.rpg.service.command;

import hex.rpg.core.domain.EntityFactory;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.Story;
import hex.rpg.service.command.campaign.SaveCampaignCommand;
import hex.rpg.service.support.PuHandlerFactory;

/**
 *
 * @author hln
 */
public class Main {
    private static final PuHandlerFactory PU_HANDLER_FACTORY = new PuHandlerFactory();
    public static CommandExecutor commandExecutor = new CommandExecutor(PU_HANDLER_FACTORY);

    public static void main(String[] args) {
        Campaign c = EntityFactory.createCampaign("Testkampanj");
        Supplement cs = EntityFactory.createCampaignSupplement("Kampanjbilaga");
        c.addSupplement(cs);
        cs.setContent("Innehåll i kampanjbilagan".getBytes());
        Story s = EntityFactory.createStory("Teststory");
        c.addStory(s);
        Supplement ss = EntityFactory.createStorySupplement("Storybilaga");
        s.addSupplement(ss);
        ss.setContent("Innehåll i storybilagan".getBytes());
        Episode e = EntityFactory.createEpisode("Episod 2");
        s.addEpisode(e);
        Supplement es = EntityFactory.createEpisodeSupplement("Episodbilaga");
        e.addSupplement(es);
        es.setContent("Innehåll i episodbilagan".getBytes());
        Episode e2 = EntityFactory.createEpisode("Episod 1");
        s.addEpisode(0, e2);
        commandExecutor.executeInTransaction(new SaveCampaignCommand(c), "DnD");
        Episode e3 = EntityFactory.createEpisode("Episod 0");
        s.addEpisode(0, e3);
        commandExecutor.executeInTransaction(new SaveCampaignCommand(c), "DnD");
    }
}
