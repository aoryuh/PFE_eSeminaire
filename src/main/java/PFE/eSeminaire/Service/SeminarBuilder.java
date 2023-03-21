package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

@Service
public class SeminarBuilder {

    @Autowired UserService userService;
    @Autowired TeamService teamService;
    @Autowired SeminarService seminarService;

    public Seminar build(MultipartFile multipartFile) throws IOException {Seminar seminar = new Seminar();
        ArrayList<User> authors = new ArrayList();
        ArrayList<String> links = new ArrayList();
        File file = new File("src/main/resources/seminarFile/" + multipartFile.getOriginalFilename());
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        BufferedReader reader;

        reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while (!line.equals("-- FIN --")) {

            if (line.equals("NOM:")){
                line = reader.readLine();
                if(teamService.teamIsPresentByName(line)){
                    seminar.setTeam(teamService.getByName(line));
                }
            }
            else if (line.equals("MAIL:")){
                line = reader.readLine();
                if(userService.userIsPresentByMail(line)){
                    authors.add(userService.findByMail(line).get());
                }
                line = reader.readLine();
                while(!line.equals("")){
                    if(userService.userIsPresentByMail(line)){
                        authors.add(userService.findByMail(line).get());
                    }
                    line = reader.readLine();
                }
                seminar.setAuthors(authors);
            }
            else if (line.equals("DATE:")){
                line = reader.readLine();
                String day = line.split(" ")[0];
                String hour = line.split(" ")[1];
                Date date = new Date(Integer.parseInt(day.split("-")[0]),
                        Integer.parseInt(day.split("-")[1]),
                        Integer.parseInt(day.split("-")[2]),
                        Integer.parseInt(hour.split(":")[0]),
                        Integer.parseInt(hour.split(":")[1]));
                if (date.after(new Date())){
                    seminar.setDate(date);
                }
            }
            else if (line.equals("LIEU:")){
                line = reader.readLine();
                seminar.setLocation(line);
            }
            else if (line.equals("TITRE:")){
                line = reader.readLine();
                if(!seminarService.SeminarIsPresentByTitle(line)){
                    seminar.setTitle(line);
                }

            }
            else if (line.equals("RESUME:")){
                line = reader.readLine();
                seminar.setDescription(line);
            }
            else if (line.equals("LIENS:")){
                line = reader.readLine();
                links.add(line);
                line = reader.readLine();
                while(!line.equals("")){
                    links.add(line);
                    line = reader.readLine();
                }
                seminar.setOptionalContentLinks(links);
            }
            line = reader.readLine();
        }

        file.delete();
        return seminar;
    }

    public class FormatException extends Exception {
        public FormatException(String errorMessage) {
            super(errorMessage);
        }
    }
}