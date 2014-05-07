package cr.prodigious.workshop.servlet.view;

import cr.prodigious.workshop.servlet.FrontControllerServlet.Method;
import cr.prodigious.workshop.servlet.Request;
import cr.prodigious.workshop.servlet.Response;

import java.io.Serializable;

/**
 * Factory to create the View Resolver based on a View Result bean
 *
 * Date: 4/2/14
 * Time: 5:03 PM
 * @author jsanca
 */
public class ViewResolverFactory implements Serializable {

    /**
     * Resolver for errors
     */
    private class ErrorViewResolver implements ViewResolver {

        @Override
        public void doResolver(
                final ViewResult viewResult,
                final Request request,
                final Response response) {

             response.error((Integer)viewResult.getValue());
        } // doResolver.
    } // ErrorViewResolver.

    /**
     * Resolver for forward
     */
    private class ForwardViewResolver implements ViewResolver {

        @Override
        public void doResolver(
                final ViewResult viewResult,
                final Request request,
                final Response response) {

            response.forward((String)viewResult.getValue());
        } // doResolver.
    } // ForwardViewResolver.

    /**
     * Resolver for redirect
     */
    private class RedirectViewResolver implements ViewResolver {

        @Override
        public void doResolver(
                final ViewResult viewResult,
                final Request request,
                final Response response) {

            response.redirect((String) viewResult.getValue());
        } // doResolver.
    } // ForwardViewResolver.


    public ViewResolver create (
            final ViewResult viewResult,
            final Request request,
            final Response response,
            final Method method) {

        ViewResolver viewResolver = null;

        if (viewResult.getType() ==
                ViewResult.ViewType.ERROR.getType()) {

             viewResolver =
                     new ErrorViewResolver();
        } else if (viewResult.getType() ==
                ViewResult.ViewType.FORWARD.getType()) {

            viewResolver =
                    new ForwardViewResolver();
        } else if (viewResult.getType() ==
                ViewResult.ViewType.REDIRECT.getType()) {

            viewResolver =
                    new RedirectViewResolver();
        } else {

            viewResolver =
                    this.doSomethingGreat (viewResolver, request, response, method);
        }

        return viewResolver;
    } // create.

    protected ViewResolver doSomethingGreat
            (final ViewResolver viewResolver,
             final Request request,
             final Response response,
             final Method method) {

        return null;
    } // doSomethingGreat.
} // E:O:F:ViewResolverFactory.
