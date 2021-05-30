package model.farm.building.stockyard.state;

import model.InGameTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class NotProducingTest {
    private NotProducing state;
    private final InGameTime zero = new InGameTime(0);

    @BeforeEach
    void setUp() {
        this.state = new NotProducing();
    }

    @Test
    void setRemainingTimeIsNoOp() {
        Assertions.assertEquals(this.zero, this.state.getRemainingTime());
        this.state.setRemainingTime(new InGameTime(20));
        Assertions.assertEquals(this.zero, this.state.getRemainingTime());
        this.state.setRemainingTime(new InGameTime(0));
        Assertions.assertEquals(this.zero, this.state.getRemainingTime());
    }


    @Test
    void changeCollectAmountIsNoOp() {
        Assertions.assertEquals(0, this.state.getCollectAmount());
        this.state.changeCollectAmount(90);
        Assertions.assertEquals(0, this.state.getCollectAmount());
        this.state.changeCollectAmount(-15);
        Assertions.assertEquals(0, this.state.getCollectAmount());
    }
}
