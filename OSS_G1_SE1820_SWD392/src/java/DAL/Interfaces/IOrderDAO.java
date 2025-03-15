package DAL.Interfaces;

import java.util.ArrayList;
import models.Entities.Order;

public interface IOrderDAO extends IDAO<Order>{
    
    ArrayList<Order> GetByUserID (int customerId);
        
}
