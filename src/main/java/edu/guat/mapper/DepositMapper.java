package edu.guat.mapper;

import edu.guat.po.Comment;
import edu.guat.po.Deposit;

import java.util.List;

public interface DepositMapper {

    List<Deposit> selectAll(String username,Integer userid,Integer year,Integer month,Integer state,Integer ispay);

    Deposit selectById(Integer id);

    Integer updateDeposit(Deposit deposit);

    Integer deleteById(Integer id);

    List<Deposit> selectMydeposit(Integer userid, String petname);

    Integer addDeposit(Deposit deposit);

    Deposit selectDetail(Integer id);

    Integer updatePrice(Double totalPrice,Integer id);

    List<Deposit> selectBySpeciesId(Integer speciesId);

    List<Deposit> selectByFoodId(Integer foodId);

    List<Deposit> selectByServiceId(Integer serviceId);

    Integer updateDepositState(Integer state, Integer id);

    Integer changePay(Integer ispay,Integer id);

    Integer reject(Integer state, String reason, Integer id);

    Integer addComment(Comment comment);

    Integer addReply(String reply, Integer cid);

    Integer updateCommentId(Integer commentId, Integer id);

    Comment selectCommentById(Integer id);

    Comment selectCommentByCid(Integer cid);

    List<Deposit > selectAllComment();

    Integer updateReply(String reply, Integer cid);

    Integer updateComment(String comment, Integer cid);
}
