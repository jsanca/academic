package monentrevue.web.services;

import monentrevue.bean.Interview;
import monentrevue.bean.Question;
import monentrevue.service.InterviewDriveService;
import monentrevue.service.UserSessionInterviewService;
import monentrevue.service.impl.UserSessionInterviewServiceImpl;
import monentrevue.web.services.bean.BooleanMessage;
import monentrevue.web.services.bean.StringMessage;
import monentrevue.web.services.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Loads in to the session a interview to get searched and requested.
 *
 * Date: 9/6/14
 * Time: 12:52 PM
 * @author jsanca
 */
@RestController
@RequestMapping("/interview")
public class SessionInterviewWebService  implements Serializable {

    @Autowired
    private InterviewDriveService interviewDriveService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BooleanMessage upload(@RequestParam("file") MultipartFile file,
                                 final HttpServletRequest request,
                                 final HttpServletResponse response) {

        boolean success = false;

        try {

            this.interviewDriveService
                    .saveInterviewFile(file.getOriginalFilename(),
                            file.getInputStream());

            success = true;
        } catch (Exception e) {

            success = false;
        }


        return new BooleanMessage(success);
    } // upload.

    @RequestMapping(value = "/load", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Object loadInterview (
            @RequestParam(value = "uuid", required = true) final String uuid,
            @RequestParam(value = "id", required = true) final String interviewNameId,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) {

        Object result = null;
        Interview interview = null;
        CustomSession customSession = null;
        UserSessionInterviewService userSessionInterviewService = null;

        if (AuthenticationUtil.isAuthenticated(request, uuid)) {

            customSession =
                    (CustomSession)request.getSession().getAttribute(uuid);

            interview =
                    this.interviewDriveService.get(interviewNameId);

            // we add this service to the session scope in
            // order to be searchable faster and easy.
            userSessionInterviewService =
                    new UserSessionInterviewServiceImpl
                            (interview);

            customSession.add(CustomSession.USER_INTERVIEW_SERVICE,
                    userSessionInterviewService);

            result = new StringMessage("Ok");
        } else {

            result = new StringMessage("User is not authenticated");
        }

        return result;
    } // getInterview.

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Object getInterview (
        @RequestParam(value = "uuid", required = true) final String uuid,
        @RequestParam(value = "id", required = true) final String interviewNameId,
        final HttpServletRequest request,
        final HttpServletResponse response
    ) {

        Object result = null;
        Interview interview = null;
        CustomSession customSession = null;
        UserSessionInterviewService userSessionInterviewService = null;

        if (AuthenticationUtil.isAuthenticated(request, uuid)) {

            customSession =
                    (CustomSession)request.getSession().getAttribute(uuid);

            interview =
                    this.interviewDriveService.get(interviewNameId);

            // we add this service to the session scope in
            // order to be searchable faster and easy.
            userSessionInterviewService =
                    new UserSessionInterviewServiceImpl
                            (interview);

            customSession.add(CustomSession.USER_INTERVIEW_SERVICE,
                    userSessionInterviewService);

            result = interview;
        } else {

            result = new StringMessage("User is not authenticated");
        }

        return result;
    } // getInterview.

    @RequestMapping(value = "/findQuestionsByType", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Object findQuestionsByType (
            @RequestParam(value = "uuid", required = true) final String uuid,
            @RequestParam(value = "questionType", required = true) final String questionType,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) {

        Object result = null;
        List<Question> questions = Collections.EMPTY_LIST;
        CustomSession customSession = null;
        UserSessionInterviewService userSessionInterviewService = null;

        if (AuthenticationUtil.isAuthenticated(request, uuid)) {

            customSession =
                    (CustomSession)request.getSession().getAttribute(uuid);

            // we add this service to the session scope in
            // order to be searchable faster and easy.
            userSessionInterviewService =
                (UserSessionInterviewService)
                        customSession.get(CustomSession.USER_INTERVIEW_SERVICE);

            if(null != userSessionInterviewService) {

                questions =
                        userSessionInterviewService.
                                findByType(questionType);
                result = questions;
            } else {

                result = new StringMessage("There is not any interview loaded");
            }
        } else {

            result = new StringMessage("User is not authenticated");
        }

        return result;
    } // findQuestionsByType.

    @RequestMapping(value = "/getQuestionTypes", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Object getQuestionTypes (
            @RequestParam(value = "uuid", required = true) final String uuid,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) {

        Object result = null;
        Set<String> questionTypes = Collections.EMPTY_SET;
        CustomSession customSession = null;
        UserSessionInterviewService userSessionInterviewService = null;

        if (AuthenticationUtil.isAuthenticated(request, uuid)) {

            customSession =
                    (CustomSession)request.getSession().getAttribute(uuid);

            // we add this service to the session scope in
            // order to be searchable faster and easy.
            userSessionInterviewService =
                    (UserSessionInterviewService)
                            customSession.get(CustomSession.USER_INTERVIEW_SERVICE);

            if(null != userSessionInterviewService) {

                questionTypes =
                        userSessionInterviewService.
                                getQuestionTypes();
                result = questionTypes;
            } else {

                result = new StringMessage("There is not any interview loaded");
            }
        } else {

            result = new StringMessage("User is not authenticated");
        }

        return result;
    } // getQuestionTypes.

    @RequestMapping(value = "/findQuestionById", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Object findQuestionById (
            @RequestParam(value = "uuid", required = true) final String uuid,
            @RequestParam(value = "id", required = true)   final String questionNameId,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) {

        Object result = null;
        CustomSession customSession = null;
        UserSessionInterviewService userSessionInterviewService = null;
        Question question = null;

        if (AuthenticationUtil.isAuthenticated(request, uuid)) {

            customSession =
                    (CustomSession)request.getSession().getAttribute(uuid);

            // we add this service to the session scope in
            // order to be searchable faster and easy.
            userSessionInterviewService =
                    (UserSessionInterviewService)
                            customSession.get(CustomSession.USER_INTERVIEW_SERVICE);

            if(null != userSessionInterviewService) {

                question =
                        userSessionInterviewService.
                                findBy(questionNameId);
                result = question;
            } else {

                result = new StringMessage("There is not any interview loaded");
            }
        } else {

            result = new StringMessage("User is not authenticated");
        }

        return result;
    } // findQuestionById.


    @RequestMapping(value = "/search", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Object searchQuestions (
            @RequestParam(value = "uuid", required = true) final String uuid,
            @RequestParam(value = "q", required = true)   final String queryTerm,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) {

        Object result = null;
        CustomSession customSession = null;
        UserSessionInterviewService userSessionInterviewService = null;
        List<Question> questions = null;

        if (AuthenticationUtil.isAuthenticated(request, uuid)) {

            customSession =
                    (CustomSession)request.getSession().getAttribute(uuid);

            // we add this service to the session scope in
            // order to be searchable faster and easy.
            userSessionInterviewService =
                    (UserSessionInterviewService)
                            customSession.get(CustomSession.USER_INTERVIEW_SERVICE);

            if(null != userSessionInterviewService) {

                questions =
                        userSessionInterviewService.
                                search(queryTerm);
                result = questions;
            } else {

                result = new StringMessage("There is not any interview loaded");
            }
        } else {

            result = new StringMessage("User is not authenticated");
        }

        return result;
    } // searchQuestions.

    @RequestMapping(value = "/findNextQuestion", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Object findNextQuestion (
            @RequestParam(value = "uuid", required = true) final String uuid,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) {

        Object result = null;
        CustomSession customSession = null;
        UserSessionInterviewService userSessionInterviewService = null;
        Question question = null;

        if (AuthenticationUtil.isAuthenticated(request, uuid)) {

            customSession =
                    (CustomSession)request.getSession().getAttribute(uuid);

            // we add this service to the session scope in
            // order to be searchable faster and easy.
            userSessionInterviewService =
                    (UserSessionInterviewService)
                            customSession.get(CustomSession.USER_INTERVIEW_SERVICE);

            if(null != userSessionInterviewService) {

                question =
                        userSessionInterviewService.
                                findNext();

                if (null == question) {

                    result = new StringMessage("There is not any more questions");
                } else {

                    result = question;
                }
            } else {

                result = new StringMessage("There is not any interview loaded");
            }
        } else {

            result = new StringMessage("User is not authenticated");
        }

        return result;
    } // findNextQuestion.

    @RequestMapping(value = "/findRandomQuestion", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Object findRandomQuestion (
            @RequestParam(value = "uuid", required = true) final String uuid,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) {

        Object result = null;
        CustomSession customSession = null;
        UserSessionInterviewService userSessionInterviewService = null;
        Question question = null;

        if (AuthenticationUtil.isAuthenticated(request, uuid)) {

            customSession =
                    (CustomSession)request.getSession().getAttribute(uuid);

            // we add this service to the session scope in
            // order to be searchable faster and easy.
            userSessionInterviewService =
                    (UserSessionInterviewService)
                            customSession.get(CustomSession.USER_INTERVIEW_SERVICE);

            if(null != userSessionInterviewService) {

                question =
                        userSessionInterviewService.
                                random();

                result = question;
            } else {

                result = new StringMessage("There is not any interview loaded");
            }
        } else {

            result = new StringMessage("User is not authenticated");
        }

        return result;
    } // findNextQuestion.
} // E:O:F:SessionInterviewWebService
