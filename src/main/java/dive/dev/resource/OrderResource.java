package dive.dev.resource;

import dive.dev.entity.Order;
import dive.dev.entity.OrderItem;
import io.quarkus.oidc.runtime.OidcAuthenticationMechanism;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/order")
//@Authenticated
public class OrderResource {

    @GET
    @Path("/{restaurantId}/list")
    @SecurityRequirement(name = "Keycloak")
    //@RolesAllowed("manager")
    public List<Order> getOrders(@PathParam("restaurantId") Long restaurantId) {
        return Order.find("restaurantId = ?1", restaurantId).list();
    }

    @GET
    @Path("/{orderId}")
    @SecurityRequirement(name = "Keycloak")
    //@RolesAllowed("manager")
    public Order getOrderDetails(@PathParam("orderId") Long orderId) {
        Order order = Order.findById(orderId);
        order.setOrderItems(OrderItem.find("orderId = ?1", orderId).list());
        return order;
    }

    @POST
    @Transactional
    @SecurityRequirement(name = "Keycloak")
    public Order createOrder(Order order) {
        order.persist();
        List<OrderItem> orderItems = order.getOrderItems();
        orderItems.forEach(orderItem -> {
            orderItem.setOrderId(order.id);
            orderItem.persist();
        });
        return order;
    }
}
