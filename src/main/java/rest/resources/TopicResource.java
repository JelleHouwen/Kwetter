package rest.resources;

import Models.Topic;
import Models.User;
import service.TopicService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
@Stateless
@Path("/topics")
public class TopicResource {
    @Inject
    TopicService topicService;

    @GET
    @Path("getTrendingTopics")
    @Produces({APPLICATION_JSON})
    public Response getTrendingTopics() {
        List<Topic> trendingTopics = topicService.getTrendingTopics();
      try{
            return Response.ok(trendingTopics, APPLICATION_JSON).build();
        } catch (Exception e){
          return Response.status(Response.Status.BAD_REQUEST).entity(new ArrayList<Topic>()).build();
      }
    }
    @POST
    @Path("addTopic")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addTopic(Topic topic) {
        try{
            this.topicService.addTopic(topic);
            return Response.ok(topic, APPLICATION_JSON).build();
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("topic not added").build();
        }
    }
}
