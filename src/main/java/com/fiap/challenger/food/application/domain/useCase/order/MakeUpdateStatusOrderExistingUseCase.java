package com.fiap.challenger.food.application.domain.useCase.order;

import com.fiap.challenger.food.common.StatusOrderEnum;
import com.fiap.challenger.food.common.form.UpdateStatusOrderFormDto;
import com.fiap.challenger.food.infraestruture.presentation.OrderPresentation;
import com.fiap.challenger.food.infraestruture.repository.OrderRepositoryDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MakeUpdateStatusOrderExistingUseCase {
    private final OrderPresentation orderPresentation;
    private static final Logger logger = LoggerFactory.getLogger(MakeUpdateStatusOrderExistingUseCase.class);

    @Autowired
    public MakeUpdateStatusOrderExistingUseCase(OrderPresentation orderPresentation) {
        this.orderPresentation = orderPresentation;
    }

    public ResponseEntity statusUpdate(UpdateStatusOrderFormDto updateStatusOrderFormDto) {
        StatusOrderEnum statusOrderEnum;
        if(getvalidStatusOrderEnum(updateStatusOrderFormDto.getStatusOrder())){
            statusOrderEnum = StatusOrderEnum.valueOf(updateStatusOrderFormDto.getStatusOrder().toUpperCase());
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        OrderRepositoryDb orderRepositoryDb = orderPresentation.findbyId(updateStatusOrderFormDto.getIdOrder());
        if(orderRepositoryDb.getId() == null){
            logger.info("Pedido informado nao existe");
            return new ResponseEntity<>(orderRepositoryDb, HttpStatus.NO_CONTENT);
        }

        if(isValidUpdateStatus(orderRepositoryDb, statusOrderEnum)){ //todo melhorar regra de atualizacao entre status
            return orderPresentation.updateStatusOrder(orderRepositoryDb.getId(), statusOrderEnum);
        }
        else{
            return new ResponseEntity<>(orderRepositoryDb, HttpStatus.NO_CONTENT);
        }
    }

    private boolean isValidUpdateStatus(OrderRepositoryDb orderRepositoryDb, StatusOrderEnum newStatus) {
        if ((newStatus.equals(StatusOrderEnum.RECEBIDO)
                && orderRepositoryDb.getStatusOrderEnum().equals(StatusOrderEnum.RECEBIDO))
                || orderRepositoryDb.getStatusOrderEnum().equals(StatusOrderEnum.EM_PREPARACAO)
                || orderRepositoryDb.getStatusOrderEnum().equals(StatusOrderEnum.PRONTO)) {
            logger.info("Pedido no status RECEBIDO ou nao possivel retroceder de status");
            return false;
        }
        if (newStatus.equals(StatusOrderEnum.EM_PREPARACAO)
                && orderRepositoryDb.getStatusOrderEnum().equals(StatusOrderEnum.PRONTO)) {
            logger.info("Pedido no status PRONTO, nao é possivel retroceder de status");
            return false;
        }
        if (newStatus.equals(StatusOrderEnum.EM_PREPARACAO)
                && orderRepositoryDb.getStatusOrderEnum().equals(StatusOrderEnum.RECEBIDO)) {
            logger.info("Atualizando status do pedido de:REBECIDO p:EM_PREPARACAO");
            return true;
        }
        if (newStatus.equals(StatusOrderEnum.PRONTO)) {
            logger.info("Atualizando pedido para status PRONTO");
            return true;
        }
        return false;
    }

    private boolean getvalidStatusOrderEnum(String status) {
        try {
            StatusOrderEnum.valueOf(status.toUpperCase());
            return true;
        } catch (Exception e) {
            logger.error("StatusOrder informado nao existe: " + e.getMessage());
            return false;
        }
    }
}
