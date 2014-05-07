package cr.prodigious.workshop.servlet;

import cr.prodigious.workshop.servlet.view.ViewResolver;
import cr.prodigious.workshop.servlet.view.ViewResolverFactory;
import cr.prodigious.workshop.servlet.view.ViewResult;
import cr.prodigious.workshop.servlet.wrapper.RequestWrapper;
import cr.prodigious.workshop.servlet.wrapper.ResponseWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The Front Controller Servlet, based on a servlet mapping will look up and dispatch a specific controller.
 *
 * Date: 4/2/14
 * Time: 3:57 PM
 * @author jsanca
 */
public class FrontControllerServlet extends HttpServlet {

    public static enum Method { POST, GET, PUT, DELETE };


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doAction(req, resp, Method.GET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doAction(req, resp, Method.POST);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doAction(req, resp, Method.PUT);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doAction(req, resp, Method.DELETE);
    }

    protected void doAction (
            final HttpServletRequest req,
            final HttpServletResponse resp,
            final Method method) {

        final Request request = this.wrap(req);
        final Response response = this.wrap(resp, req);

        final ControllerFactory controllerFactory =
                this.getControllerFactory (request, response);

        final MappingResult mappingResult =
                controllerFactory.create(request, response, method);

         if (null != mappingResult) {

             this.handleWorkflow
                     (mappingResult, request, response, method);
         }

    } // doDispatch.

    private void handleWorkflow(
            final MappingResult mappingResult,
            final Request request,
            final Response response,
            final Method method) {

        ChainResult preChainResult = null;
        ChainResult postChainResult = null;
        ViewResult viewResult = null;
        boolean continueWorkFlow = true;

        if (null != mappingResult.getController()) {

            // executes preconditions
            if (null != mappingResult.getPreconditionCommand()) {

                preChainResult = mappingResult
                        .getPreconditionCommand()
                        .execute(request, response);
            }

            continueWorkFlow =
                    this.evalPreconditions (preChainResult,
                                    request, response,
                                    method);

            if (continueWorkFlow) {

                viewResult = // execute the invariant, the controller
                        mappingResult.getController()
                                .execute(request, response);

                // execute the post conditions
                if (null != mappingResult.getPostconditionCommand()) {

                    postChainResult = mappingResult
                            .getPostconditionCommand()
                            .execute(request, response);
                }

                this.evalInvariantAndPost (viewResult, postChainResult,
                        request, response, method);
            }
        }
    } // handleWorkflow.

    private boolean evalPreconditions(
            final ChainResult chainResult,
            final Request request,
            final Response response,
            final Method method) {

        boolean continueWorkFlow = true;

        if (null != chainResult) {

            if (ChainResult.ChainType.STOP == chainResult.getType()) {

                continueWorkFlow = false;

                if (null != chainResult.getViewResult()) {

                    this.processView (chainResult.getViewResult(),
                            request, response, method);
                }
            }
        }
        return continueWorkFlow;
    } // evalPreconditions.


    private void evalInvariantAndPost(
            final ViewResult viewResult,
            final ChainResult postChainResult,
            final Request request,
            final Response response,
            final Method method) {

        boolean continueWorkFlow = true;
        if (null != postChainResult) {

            if (ChainResult.ChainType.STOP == postChainResult.getType()) {

                continueWorkFlow = false;

                if (null != postChainResult.getViewResult()) {

                    this.processView (postChainResult.getViewResult(),
                            request, response, method);
                }
            }
        }

        if (continueWorkFlow) {

            this.processView(viewResult, request, response, method);
        }

    } // evalInvariantAndPost.

    private void processView(
            final ViewResult viewResult,
            final Request request,
            final Response response,
            final Method method) {

        ViewResolver viewResolver = null;

        if (null != viewResult) {

            viewResolver =
                    this.getViewResolverFactory(request, response).
                    create(viewResult, request, response, method);

            if (null != viewResolver) {

                viewResolver.doResolver
                        (viewResult, request, response);
            }
        }
    } // processView.

    protected ControllerFactory getControllerFactory(
            final Request request, final Response response) {

        final Context context =
                request.getContext ();

        ControllerFactory controllerFactory =
                context.get("controllerFactory",
                        ControllerFactory.class,
                        this.getFactoryParameters
                                (request, response));

        return controllerFactory;
    } // getControllerFactory.

    protected Object [] getFactoryParameters(final Request request, final Response response) {

        return null;
    }

    protected ViewResolverFactory getViewResolverFactory(
            final Request request, final Response response) {

        final Context context =
                request.getContext ();

        ViewResolverFactory viewResolverFactory =
                context.get("viewResolverFactory", ViewResolverFactory.class);

        return viewResolverFactory;
    } // getViewResolverFactory.

    protected Response wrap(HttpServletResponse resp, HttpServletRequest req) {

        return new ResponseWrapper(resp, req);
    } // wrap.

    protected Request wrap(HttpServletRequest req) {

        return new RequestWrapper(req);
    } // wrap.

} // E:O:F:DispatcherServlet.
