package com;

import model.Project; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Projects") 

public class ProjectService 
{ 
  Project projectObj = new Project(); 
 
  @GET
  @Path("/") 
  @Produces(MediaType.TEXT_HTML) 
  public String readProjects() 
  { 
	  return projectObj.readProjects();  
  } 

 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertProject(@FormParam("projectCode") String projectCode, 
                          @FormParam("projectName") String projectName, 
                          @FormParam("researcherID") String researcherID, 
                          @FormParam("projectDesc") String projectDesc,
                          @FormParam("categoryID") String categoryID, 
                          @FormParam("projectRemarks") String projectRemarks)
{ 
    String output = projectObj.insertProject(projectCode, projectName, researcherID, projectDesc,categoryID,projectRemarks  ); 
    return output; 
}

 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updateProject(String projectData) 
 { 
   //Convert the input string to a JSON object 
        JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject(); 
  //Read the values from the JSON object
        String projectID = projectObject.get("projectID").getAsString(); 
        String projectCode = projectObject.get("projectCode").getAsString(); 
        String projectName = projectObject.get("projectName").getAsString(); 
        String researcherID = projectObject.get("researcherID").getAsString(); 
        String projectDesc = projectObject.get("projectDesc").getAsString(); 
        String categoryID = projectObject.get("categoryID").getAsString(); 
        String projectRemarks = projectObject.get("projectRemarks").getAsString();
   
        String output = projectObj.updateProject(projectID, projectCode, projectName, researcherID, projectDesc,categoryID,projectRemarks  ); 
   
        return output; 
}

@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteProject(String projectData) 
 { 
     //Convert the input string to an XML document
            Document doc = Jsoup.parse(projectData, "", Parser.xmlParser()); 

    //Read the value from the element <projectID>
        String projectID = doc.select("projectID").text(); 
        
        String output = projectObj.deleteProject(projectID); 

     return output; 
}



}