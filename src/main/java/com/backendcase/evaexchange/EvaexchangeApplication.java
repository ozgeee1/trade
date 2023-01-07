package com.backendcase.evaexchange;

import com.backendcase.evaexchange.domain.BuyOrder;
import com.backendcase.evaexchange.domain.Portfolio;
import com.backendcase.evaexchange.domain.PortfolioShare;
import com.backendcase.evaexchange.domain.SellOrder;
import com.backendcase.evaexchange.domain.Share;
import com.backendcase.evaexchange.domain.User;
import com.backendcase.evaexchange.service.BuyOrderServiceImpl;
import com.backendcase.evaexchange.service.PorfolioServiceImpl;
import com.backendcase.evaexchange.service.PortfolioShareServiceImpl;
import com.backendcase.evaexchange.service.SellOrderServiceImpl;
import com.backendcase.evaexchange.service.ShareServiceImpl;
import com.backendcase.evaexchange.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class EvaexchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaexchangeApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserServiceImpl userService, ShareServiceImpl shareService,
			PorfolioServiceImpl porfolioService,
			PortfolioShareServiceImpl portfolioShareService,
			SellOrderServiceImpl sellOrderService,
			BuyOrderServiceImpl buyOrderService){
		return args -> {
			User user1 = User.builder().id(1L)
					.firstName("Özge")
					.lastName("Özkan")
					.balance(BigDecimal.valueOf(100000))
					.build();

			User user2 = User.builder().id(2L)
					.firstName("Onur")
					.lastName("Topal")
					.balance(BigDecimal.valueOf(100000))
					.build();

			User user3 = User.builder().id(3L)
					.firstName("Ayşe")
					.lastName("Ünal")
					.balance(BigDecimal.valueOf(100000))
					.build();

			User user4 = User.builder().id(4L)
					.firstName("Mehmet")
					.lastName("Erdem")
					.balance(BigDecimal.valueOf(100000))
					.build();

			User user5 = User.builder().id(5L)
					.firstName("Ezgi")
					.lastName("Sönmez")
					.balance(BigDecimal.valueOf(100000))
					.build();

			User user6 = User.builder().id(6L)
					.firstName("Hatice")
					.lastName("Ak")
					.balance(BigDecimal.valueOf(500))
					.build();

			userService.addAllUsers(List.of(user1,user2,user3,user4,user5,user6));


			Share share1 = Share.builder()
					.id(1L)
					.price(BigDecimal.valueOf(42.15))
					.registered(true)
					.symbol("THY")
					.updatedTime(LocalDateTime.now()).build();

			Share share2 = Share.builder()
					.id(2L)
					.price(BigDecimal.valueOf(26.89))
					.registered(true)
					.symbol("GAR")
					.updatedTime(LocalDateTime.now()).build();
			Share share3 = Share.builder()
					.id(3L)
					.price(BigDecimal.valueOf(14.56))
					.registered(true)
					.symbol("FRO")
					.updatedTime(LocalDateTime.of(2023,01,7,9,30)).build();
			Share share4 = Share.builder()
					.id(4L)
					.price(BigDecimal.valueOf(20.12))
					.registered(false)
					.symbol("KRD")
					.updatedTime(LocalDateTime.of(2023,1,7,14,18)).build();
			Share share5 = Share.builder()
					.id(5L)
					.price(BigDecimal.valueOf(11.78))
					.registered(false)
					.symbol("LKM")
					.updatedTime(LocalDateTime.now()).build();
			shareService.addAllShares(List.of(share1,share2,share3,share4,share5));

			Portfolio portfolioForUser1 = porfolioService.createPortfolioForUser(user1);
			Portfolio portfolioForUser2 = porfolioService.createPortfolioForUser(user2);
			Portfolio portfolioForUser3 = porfolioService.createPortfolioForUser(user3);
			Portfolio portfolioForUser4 = porfolioService.createPortfolioForUser(user4);
			Portfolio portfolioForUser5 = porfolioService.createPortfolioForUser(user5);

			PortfolioShare portfolioShare1 = PortfolioShare.builder()
					.quantity(BigDecimal.valueOf(1000))
					.portfolio(portfolioForUser1)
					.user(user1)
					.share(share1).build();

			PortfolioShare portfolioShare2 = PortfolioShare.builder()
					.quantity(BigDecimal.valueOf(2500))
					.portfolio(portfolioForUser2)
					.user(user2)
					.share(share1).build();

			PortfolioShare portfolioShare3 = PortfolioShare.builder()
					.quantity(BigDecimal.valueOf(3000))
					.portfolio(portfolioForUser3)
					.user(user3)
					.share(share2).build();

			PortfolioShare portfolioShare4 = PortfolioShare.builder()
					.quantity(BigDecimal.valueOf(5000))
					.portfolio(portfolioForUser4)
					.user(user4)
					.share(share2).build();

			PortfolioShare portfolioShare5 = PortfolioShare.builder()
					.quantity(BigDecimal.valueOf(3000))
					.portfolio(portfolioForUser5)
					.user(user5)
					.share(share3).build();

			PortfolioShare portfolioShare6 = PortfolioShare.builder()
					.quantity(BigDecimal.valueOf(1200))
					.portfolio(portfolioForUser1)
					.user(user1)
					.share(share3).build();

			PortfolioShare portfolioShare7 = PortfolioShare.builder()
					.quantity(BigDecimal.valueOf(4000))
					.portfolio(portfolioForUser2)
					.user(user2)
					.share(share2).build();


			portfolioShareService.addPortfolioShares(List.of(portfolioShare1,portfolioShare2,portfolioShare3,portfolioShare4,portfolioShare5,portfolioShare6,portfolioShare7));

		SellOrder sellOrderUser1 = SellOrder.builder()
				.user(user1)
				.share(share1).build();
			SellOrder sellOrderUser2 = SellOrder.builder()
					.user(user2)
					.share(share1).build();
			SellOrder sellOrderUser3 = SellOrder.builder()
					.user(user3)
					.share(share2).build();
			SellOrder sellOrderUser4 = SellOrder.builder()
					.user(user4)
					.share(share2).build();
			SellOrder sellOrderUser5 = SellOrder.builder()
					.user(user5)
					.share(share3).build();

			sellOrderService.giveSellOrder(BigDecimal.valueOf(20),sellOrderUser1);
			sellOrderService.giveSellOrder(BigDecimal.valueOf(30),sellOrderUser2);
			sellOrderService.giveSellOrder(BigDecimal.valueOf(50),sellOrderUser3);
			sellOrderService.giveSellOrder(BigDecimal.valueOf(10),sellOrderUser4);
			sellOrderService.giveSellOrder(BigDecimal.valueOf(60),sellOrderUser5);


			BuyOrder buyOrderUser1 = BuyOrder.builder()
					.share(share3)
					.user(user1).build();

			BuyOrder buyOrderUser2 = BuyOrder.builder()
					.share(share3)
					.user(user2).build();

			BuyOrder buyOrderUser3 = BuyOrder.builder()
					.share(share1)
					.user(user3).build();

			BuyOrder buyOrderUser4 = BuyOrder.builder()
					.share(share2)
					.user(user4).build();

			BuyOrder buyOrderUser5 = BuyOrder.builder()
					.share(share1)
					.user(user5).build();

			buyOrderService.giveBuyOrder(5L,BigDecimal.valueOf(50),buyOrderUser1);
			buyOrderService.giveBuyOrder(5L,BigDecimal.valueOf(10),buyOrderUser2);
			buyOrderService.giveBuyOrder(1L,BigDecimal.valueOf(20),buyOrderUser3);
			buyOrderService.giveBuyOrder(3L,BigDecimal.valueOf(30),buyOrderUser4);
			buyOrderService.giveBuyOrder(2L,BigDecimal.valueOf(50),buyOrderUser5);


		};
	}
}
