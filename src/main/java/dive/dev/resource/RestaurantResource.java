package dive.dev.resource;

import dive.dev.entity.Menu;
import dive.dev.entity.MenuItem;
import dive.dev.entity.Restaurant;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.math.BigDecimal;
import java.util.List;

@Path("/restaurant")
//@Authenticated
public class RestaurantResource {


    @GET
    @Path("/public/list")
    //@PermitAll
    public List<Restaurant> getRestaurants() {
        return Restaurant.listAll();
    }

    @GET
    @Path("/public/menu/{restaurantId}")
    //@PermitAll
    public Menu getMenu(@PathParam("restaurantId") Long restaurantId) {
        Menu menu = Menu.find("restaurantId = ?1 and active = 1", restaurantId).firstResult();
        menu.setMenuItems(MenuItem.find("menuId = ?1", menu.id).list());
        return menu;
    }

    @POST
    @Transactional
    //@RolesAllowed("admin")
    public Restaurant createRestaurant(Restaurant restaurant) {
        restaurant.persist();
        return restaurant;
    }

    @POST
    @Path("/menu")
    @Transactional
    //@RolesAllowed("manager")
    public Menu createMenu(Menu menu) {
        menu.persist();
        menu.getMenuItems().forEach(menuItem -> {
            menuItem.setMenuId(menu.id);
            menuItem.persist();
        });
        return menu;
    }

    @PUT
    @Path("/menu/item/{itemId}/{price}")
    @Transactional
    @SecurityRequirement(name = "Keycloak")
    //@RolesAllowed("owner")
    public MenuItem createMenuItem(@PathParam("itemId") Long itemId
            , @PathParam("price") BigDecimal price) {
        MenuItem menuItem = MenuItem.findById(itemId);
        menuItem.setPrice(price);
        menuItem.persist();
        return menuItem;
    }

}
