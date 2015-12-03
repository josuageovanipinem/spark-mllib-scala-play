/**
 * mapping to global *Routes* object from play generated jsRoutes
 *
 * @type {{classify: function}}
 */
export var Routes = {
    authenticated: jsRoutes.controllers.Twitter.authenticated().url,
    classify: (keyword) => jsRoutes.controllers.Application.classify(keyword).url,
    keys: jsRoutes.controllers.Twitter.keys().url,
    logout: jsRoutes.controllers.Twitter.logout().url
};