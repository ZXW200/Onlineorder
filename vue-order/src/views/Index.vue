<template>
  <div class="app">
    <div class="top">
      <div id="menu">
        <div class="name">Pastry Restaurant</div>
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
        >
          <el-menu-item index="1">WELCOME</el-menu-item>
          <el-menu-item index="2">SPECIALTIES</el-menu-item>
          <el-menu-item index="3">MENU</el-menu-item>
          <el-menu-item index="4">RESERVE</el-menu-item>
        </el-menu>
        <div class="right">
          <el-button class="back" @click="back" size="mini">BACK</el-button>
        </div>
      </div>

      <!-- 轮播图 -->
      <div class="banner">
        <el-carousel height="1000px" indicator-position="none">
          <el-carousel-item v-for="item in 3" :key="item"> </el-carousel-item>
        </el-carousel>
      </div>
    </div>

    <!-- 关于 -->
    <div class="about">
      <div class="text">
        <h1>OUR STORE</h1>
        <span>* * *</span>
        <p>
          Welcome to Pastry Restaurant. We are committed to providing you with an unforgettable dining experience, allowing every guest to feel the warmth and joy of home. <br />
          Since its opening, we have always insisted on using the freshest ingredients and unique cooking techniques to present authentic Western cuisine to you. <br />
          The single inspiration comes from the essence of classic Western cuisine, while incorporating modern innovative elements, aiming to satisfy various taste buds. <br />

        </p>
        <div>ABOUT US</div>
      </div>
      <div>
        <img src="../assets/images/img_1.jpg" alt="" />
      </div>
    </div>

    <!-- 菜品展示 -->
    <div class="menuShow" ref="show">
      <div class="bgcOne"></div>
      <h3 class="headline">OUR SPECIALTIES</h3>
      <div class="half">
        <div class="one box">
          <img src="../assets/images/img_square_1.jpg" />
          <div class="text text-center">
            <h3>Baked Potato Pizza</h3>
            <p>
              Roasted potato pizza is a unique and delicious twist of traditional pizza, blending the comfortable flavor of roasted potatoes. Using typical baked potato toppings, spread cheddar cheese, yogurt, crispy bacon chunks, and scallions on top of the pizza crust.
            </p>
            <p class="price">$20.45</p>
          </div>
        </div>

        <div class="two box">
          <img src="../assets/images/img_square_2.jpg" />
          <div class="text text-center">
            <h3>Salted Fried Chicken</h3>
            <p>
              Season with salt. This basic method highlights the natural flavor of chicken and enhances its flavor through a golden and crispy coating.
            </p>
            <p class="price">$18.45</p>
          </div>
        </div>

        <div class="three box">
          <div class="text text-center">
            <h3>Imported Salmon Steak</h3>
            <p>
              Using high-quality salmon from Norway with salt and freshly ground black pepper for frying and grilling
            </p>
            <p class="price">$22.55</p>
          </div>
          <img src="../assets/images/img_square_3.jpg" />
        </div>

        <div class="four box">
          <div class="text text-center">
            <h3>Roast Beef (4 sticks)</h3>
            <p>
              Four skewers of roasted beef seasoned with garlic powder, onion powder, and dried vanilla
            </p>
            <p class="price">$32.45</p>
          </div>
          <img src="../assets/images/img_square_4.jpg" />
        </div>

        <div class="five box">
          <img src="../assets/images/img_square_5.jpg" />
          <div class="text text-center">
            <h3>Tuna Roast Source</h3>
            <p>
              Fresh tuna grilled over high heat to preserve its freshness
            </p>
            <p class="price">$22.4</p>
          </div>
        </div>

        <div class="six box">
          <img src="../assets/images/img_square_6.jpg" />
          <div class="text text-center">
            <h3>Fried Potatoes with Garlic</h3>
            <p>
              Crispy and garlic flavored fried potatoes
            </p>
            <p class="price">$12.45</p>
          </div>
        </div>
      </div>
      <div class="bgcTwo"></div>
    </div>

    <!-- 菜单 -->
    <div class="menus" ref="menu">
      <h2>OUR MENU</h2>
      <el-tabs v-model="activeName" :stretch="true" @tab-click="handleClick">
        <!-- coffer -->
        <el-tab-pane label="coffer" name="first">
          <el-table
            ref="cartTable"
            :header-cell-style="{ 'text-align': 'center' }"
            :cell-style="{ 'text-align': 'center' }"
            :data="coffer"
            size="medium"
            style="width: 100%; margin: 0 auto"
          >
            <el-table-column label="productImage" width="180">
              <template slot-scope="scope">
                <el-image
                  v-if="scope.row.productImage"
                  :src="productImg(scope.row.productImage)"
                  style="width: 100px; height: 100px"
                >
                </el-image>
                <el-image
                  v-else
                  :src="require('../assets/images/img_square_5.jpg')"
                  style="width: 100px; height: 100px"
                >
                </el-image>
              </template>
            </el-table-column>
            <el-table-column label="productName" prop="name"></el-table-column>
            <el-table-column
              label="productDescribe"
              prop="description"
            ></el-table-column>
            <el-table-column label="price" prop="price"></el-table-column>
            <el-table-column label="count" width="220">
              <template slot-scope="scope">
                <el-input-number
                  setp="1"
                  :min="0"
                  v-model="scope.row.num"
                  plain
                  @change="calTotalPrice()"
                ></el-input-number>
              </template>
            </el-table-column>
            <el-table-column label="price">
              <template slot-scope="scope">
                <span>{{ scope.row.num * scope.row.price }}</span>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>

        <!-- food -->
        <el-tab-pane label="food" name="second">
          <el-table
            ref="cartTable"
            :header-cell-style="{ 'text-align': 'center' }"
            :cell-style="{ 'text-align': 'center' }"
            :data="food"
            size="medium"
            style="width: 100%; margin: 0 auto"
          >
            <el-table-column label="productImage" width="180">
              <template slot-scope="scope">
                <el-image
                  v-if="scope.row.productImage"
                  :src="productImg(scope.row.productImage)"
                  style="width: 100px; height: 100px"
                >
                </el-image>
                <el-image
                  v-else
                  :src="require('../assets/images/img_square_5.jpg')"
                  style="width: 100px; height: 100px"
                >
                </el-image>
              </template>
            </el-table-column>
            <el-table-column label="productName" prop="name"></el-table-column>
            <el-table-column
              label="productDescribe"
              prop="description"
            ></el-table-column>
            <el-table-column label="price" prop="price"></el-table-column>
            <el-table-column label="count" width="220">
              <template slot-scope="scope">
                <el-input-number
                  setp="1"
                  :min="0"
                  v-model="scope.row.num"
                  plain
                  @change="calTotalPrice()"
                ></el-input-number>
              </template>
            </el-table-column>
            <el-table-column label="price">
              <template slot-scope="scope">
                <span>{{ scope.row.num * scope.row.price }}</span>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>

        <!-- dessert -->
        <el-tab-pane label="dessert" name="third">
          <el-table
            ref="cartTable"
            :header-cell-style="{ 'text-align': 'center' }"
            :cell-style="{ 'text-align': 'center' }"
            :data="dessert"
            size="medium"
            style="width: 100%; margin: 0 auto"
          >
            <el-table-column label="productImage" width="180">
              <template slot-scope="scope">
                <el-image
                  v-if="scope.row.productImage"
                  :src="productImg(scope.row.productImage)"
                  style="width: 100px; height: 100px"
                >
                </el-image>
                <el-image
                  v-else
                  :src="require('../assets/images/img_square_5.jpg')"
                  style="width: 100px; height: 100px"
                >
                </el-image>
              </template>
            </el-table-column>
            <el-table-column label="productName" prop="name"></el-table-column>
            <el-table-column
              label="productDescribe"
              prop="description"
            ></el-table-column>
            <el-table-column label="price" prop="price"></el-table-column>
            <el-table-column label="count" width="220">
              <template slot-scope="scope">
                <el-input-number
                  setp="1"
                  :min="0"
                  v-model="scope.row.num"
                  plain
                  @change="calTotalPrice()"
                ></el-input-number>
              </template>
            </el-table-column>
            <el-table-column label="price">
              <template slot-scope="scope">
                <span>{{ scope.row.num * scope.row.price }}</span>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <!-- 结算 -->
        <div class="cart-filter-bar-bottom">
          <el-form
            ref="form"
            :model="placeOrder"
            :rules="rules"
            label-width="120px"
          >
            <el-form-item label="TABLE NO" prop="tableId">
              <el-select
                v-model="placeOrder.tableId"
                placeholder="Please choose the table number"
              >
                <el-option
                  v-for="(item, index) in tables"
                  :key="index"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <!-- <el-form-item label="STATUS" prop="status">
              <el-select v-model="placeOrder.status" placeholder="Please select status">
                <el-option v-for="item in orderStatus" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item> -->
          </el-form>
          <div class="cart-sum">
            <span class="total-text">$：</span>
            <span class="total-symbol">{{ totalPrice }}</span>
            <div @click="buy" class="pay-btn-active">Order</div>
          </div>
        </div>
      </el-tabs>
    </div>

    <div class="coffer">
      <div></div>
    </div>

    <!-- 预订 -->
    <div class="reserve" ref="reserve">
      <h3>RESERVE A TABLE</h3>
      <el-form
        ref="form"
        :model="reservation"
        :rules="rules"
        label-width="120px"
      >
        <el-form-item label="PEOPLE NO" prop="tableId">
          <el-select
            v-model="reservation.tableId"
            placeholder="Please choose the table number"
          >
            <el-option
              v-for="(item, index) in tables"
              :key="index"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="BOOKING TIME" prop="reservationTime">
          <el-date-picker
            clearable
            v-model="reservation.reservationTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="Please select the booking time"
          >
          </el-date-picker>
        </el-form-item>
        <!-- <el-form-item label="STATUS" prop="status">
          <el-select v-model="reservation.status" placeholder="Please select status">
            <el-option v-for="item in reserveStatus" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item> -->
      </el-form>
      <el-button type="primary" @click="onSubmit">RESERVE</el-button>
    </div>
  </div>
</template>

<script>
import { listItems } from "../api/canteens/items";
import { addOrders } from "../api/canteens/orders";
import { addReservations } from "../api/canteens/reservations";
import { listTables } from "../api/canteens/tables";
export default {
  data() {
    return {
      //选中数据
      selectionData: [],
      // 是否全选
      isAllChecked: false,
      // 购物车总价格
      totalPrice: 0,
      // 购物车总数量
      totalNum: 0,
      tables: [],
      // 预订
      reservation: {
        userId: this.$route.query.userId,
        tableId: "",
        reservationTime: "",
        status: "PENDING",
      },
      // 订单
      placeOrder: {
        userId: this.$route.query.userId,
        tableId: "",
        orderTime: "",
        status: "PENDING",
      },

      activeName: "first",
      activeIndex: "1",
      menuItemId: "",
      price: 0,
      // 分类:咖啡
      coffer: [],
      // 分类:食物
      food: [],
      // 分类:甜点
      dessert: [],
      // 表单校验
      rules: {
        tableId: [
          {
            required: true,
            message: "The table number cannot be empty",
            trigger: "change",
          },
        ],
        reservationTime: [
          {
            required: true,
            message: "The reservation time cannot be empty",
            trigger: "change",
          },
        ],
        status: [
          {
            required: true,
            message: "The state cannot be empty",
            trigger: "change",
          },
        ],
      },
    };
  },
  created() {
    this.menuList();
    this.tableList();
    this.time();
    this.calTotalPrice();
  },
  methods: {
    // 计算总价和总数量
    calTotalPrice(value) {
      this.cofferPrice = 0;
      this.cofferNum = 0;
      this.foodPrice = 0;
      this.foodNum = 0;
      this.dessertPrice = 0;
      this.dessertNum = 0;

      this.coffer.forEach((v) => {
        this.cofferPrice += v.num * v.price;
        this.cofferNum += v.num;
      });
      this.food.forEach((v) => {
        this.foodPrice += v.num * v.price;
        this.foodNum += v.num;
      });
      this.dessert.forEach((v) => {
        this.dessertPrice += v.num * v.price;
        this.dessertNum += v.num;
      });
      // 总价
      this.totalPrice = this.cofferPrice + this.foodPrice + this.dessertPrice;
      this.totalNum = this.cofferNum + this.foodNum + this.dessertNum;
    },

    // 购买按钮
    buy() {
      if (this.$route.query.userId == undefined) {
        this.$message.error("Please log in first");
        return;
      }
      if (this.totalPrice > 0) {
        if (this.$route.query.userId) {
          this.placeOrder.userId = this.$route.query.userId;
          addOrders(this.placeOrder).then((res) => {
            if (res.status == 201) {
              // 订单详情数据
              // let orderDetails = {
              //   orderId: '1730508828920070245',
              //   menuItemId: this.menuItemId,
              //   quantity: this.totalNum,
              //   price: this.totalPrice
              // };
              // localStorage.setItem("orderDetails", JSON.stringify(orderDetails))
              this.$message({
                message: "Order Successful",
                type: "success",
              });
              setTimeout(() => {
                this.$router.go(0);
              }, 1000);
            } else {
              this.$message({
                message: "Order Failed",
                type: "error",
              });
              setTimeout(() => {
                this.$router.go(0);
              }, 1000);
            }
          });
        } else {
          this.$prompt("input userName", "Tilte", {
            confirmButtonText: "contrim",
            cancelButtonText: "cancel",
          })
            .then(({ value }) => {
              if (value) {
                this.placeOrder.userName = value;
                addOrders(this.placeOrder).then((res) => {
                  if (res.status == 201) {
                    // 订单详情数据
                    // let orderDetails = {
                    //   orderId: '1730508828920070245',
                    //   menuItemId: this.menuItemId,
                    //   quantity: this.totalNum,
                    //   price: this.totalPrice
                    // };
                    // localStorage.setItem("orderDetails", JSON.stringify(orderDetails))
                    this.$message({
                      message: "Order Successful",
                      type: "success",
                    });
                    setTimeout(() => {
                      this.$router.go(0);
                    }, 1000);
                  } else {
                    this.$message({
                      message: "Order Failed",
                      type: "error",
                    });
                    setTimeout(() => {
                      this.$router.go(0);
                    }, 1000);
                  }
                });
              }
            })
            .catch(() => {});
        }
      } else {
        this.$message({
          message: "Order Failed",
          type: "error",
        });
      }
    },

    // 菜单列表
    menuList() {
      listItems().then((res) => {
        if (res.data) {
          res.data.forEach((v) => {
            this.menuItemId = v.id;
            v.num = 0;
            if (v.category == 1 && v.available == true) {
              this.coffer.push(v);
            } else if (v.category == 2 && v.available == true) {
              this.food.push(v);
            } else if (v.category == 3 && v.available == true) {
              this.dessert.push(v);
            }
          });
        }
      });
    },

    // 餐桌列表
    tableList() {
      listTables().then((res) => {
        res.data.forEach((v) => {
          this.tables.push(v.id);
        });
      });
    },

    // 菜单分类
    handleClick(tab, event) {},

    // 点击滚动到指定位置
    handleSelect(key, keyPath) {
      if (key == 2) {
        // 到菜品展示
        let show = this.$refs.show;
        let showPosition = show.offsetTop;
        window.scrollTo({
          top: showPosition,
          behavior: "smooth",
        });
      } else if (key == 3) {
        // 到菜单
        let menu = this.$refs.menu;
        let menuPosition = menu.offsetTop;
        window.scrollTo({
          top: menuPosition,
          behavior: "smooth",
        });
      } else if (key == 4) {
        // 到预定
        let reserve = this.$refs.reserve;
        let reservePosition = reserve.offsetTop;
        window.scrollTo({
          top: reservePosition,
          behavior: "smooth",
        });
      }
    },

    // 退出登录
    back(param) {
      if (param) {
        this.$message({
          message: "Exit Successful",
          type: "success",
        });
        setTimeout(() => {
          this.$router.push("/login");
          localStorage.removeItem("token");
        }, 800);
      }
    },

    // 预订按钮
    onSubmit() {
      if (this.$route.query.userId == undefined) {
        this.$message.error("Please log in first");
        return;
      }
      addReservations(this.reservation).then((res) => {
        console.log(res);
        if (res.data.code == 0) {
          this.$message({
            message: "Book Successfully",
            type: "success",
          });
          setTimeout(() => {
            this.$router.go(0);
          }, 1000);
        } else {
          this.$message.error({
            message: "Booking Failure",
          });
          setTimeout(() => {
            this.$router.go(0);
          }, 1000);
        }
      });
    },
    productImg(src) {
      return "http://localhost:18080/temp-rainy/" + src;
    },
    // 时间格式
    time() {
      const date = new Date();
      const Y = date.getFullYear();
      const M =
        date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1)
          : date.getMonth() + 1;
      const D = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      const H = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
      const F =
        date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
      const S =
        date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      const time = Y + "-" + M + "-" + D + " " + H + ":" + F + ":" + S;
      this.placeOrder.orderTime = time;
    },
  },
  computed: {},
};
</script>

<style lang="scss" scoped>
.app {
  width: 100%;
  height: 100%;

  .top {
    position: relative;
    height: 1000px;

    // 顶部菜单
    #menu {
      display: flex;
      align-items: center;
      justify-content: space-between;

      .name {
        font-size: 20px;
        margin-left: 60px;
        font-weight: 600;
        z-index: 10;
        color: antiquewhite;
      }

      .el-menu-item {
        font-weight: 600;
        font-size: 20px;
        z-index: 10;
        color: antiquewhite;
      }

      .el-menu-item:hover {
        color: black;
      }

      .el-menu-item:last-child {
        display: flex;
      }

      .right {
        margin-right: 60px;
        z-index: 10;

        .back {
          background-color: antiquewhite;
        }
      }
    }

    // 轮播图
    .banner {
      position: absolute;
      top: 0;
      width: 100%;

      .el-carousel__item:nth-child(1n) {
        background: url("../assets/images/bg3.jpg") center;
        background-size: cover;
      }

      .el-carousel__item:nth-child(2n) {
        background: url("../assets/images/bg2.jpg") center;
        background-size: cover;
      }

      .el-carousel__item:last-child {
        background: url("../assets/images/hero_bg_2.jpg") center;
        background-size: cover;
      }
    }
  }

  // 关于
  .about {
    display: flex;
    justify-content: space-around;
    width: 60%;
    margin: 80px auto;

    .text {
      display: flex;
      flex-direction: column;
      text-align: center;
      justify-content: center;

      h1 {
        letter-spacing: 0.05em;
        font-size: 50px;
        position: relative;
        top: -30px;
        line-height: 40px;
      }

      span {
        margin: 30px 0;
        color: #888;
      }

      p {
        overflow-wrap: break-word;
        line-height: 25px;
        color: #888;
        margin-bottom: 30px;
      }

      div {
        font-size: 20px;
      }
    }
  }

  // 菜单展示
  .menuShow {
    width: 100%;
    position: relative;
    background-color: #e4e4e4;

    .headline {
      position: absolute;
      top: 350px;
      left: 50%;
      font-size: 50px;
      z-index: 100;
      transform: translate(-50%, -50%);
      color: #fff;
    }

    .bgcOne {
      margin-bottom: 700px;
      background: url("../assets/images/hero_bg_3.jpg") center;
      filter: brightness(80%);
    }

    .bgcTwo {
      background: url("../assets/images/hero_bg_5.jpg") center;
      filter: brightness(80%);
    }

    .bgcOne,
    .bgcTwo {
      width: 100%;
      height: 500px;
    }

    .half {
      z-index: 100;
      display: flex;
      flex-wrap: wrap;
      position: absolute;
      top: 50%;
      left: 50%;
      width: 50%;
      transform: translate(-50%, -50%);
      height: 800px;
      background-color: #fff;

      .box {
        display: flex;
        width: 50%;

        img {
          width: 50%;
          height: 266.666px;
        }

        .text {
          width: 50%;
          display: flex;
          flex-direction: column;
          justify-content: space-evenly;

          h3 {
            font-weight: 400;
            font-size: 22px;
            text-align: center;
          }

          p {
            text-align: center;
            color: #bcbdc1;
          }

          .price {
            color: #ffb25e;
            font-size: 20px;
            text-align: center;
          }
        }
      }
    }
  }

  // 菜单
  .menus {
    position: relative;
    width: 60%;
    margin: 80px auto;

    h2 {
      position: absolute;
      font-size: 50px;
      z-index: 100;
      top: -150px;
      left: 50%;
      color: #fff;
      transform: translate(-50%, -50%);
    }

    /* 购物车底部栏 */
    .cart-filter-bar-bottom {
      position: relative;
      width: 95%;
      display: flex;
      justify-content: end;
      margin-top: 50px;

      .el-form {
        margin-right: 50px;
        display: flex;
      }

      .cart-sum {
        display: flex;
        align-items: center;

        .total-text {
          font-size: 24px;
          color: #ffa848;
        }

        .total-symbol {
          margin-right: 15px;
          font-size: 24px;
          color: #ffa848;
        }

        /* 激活状态的支付按钮 */
        .pay-btn-active {
          width: 74px;
          height: 42px;
          line-height: 42px;
          color: #fff;
          background: #ffa848;
          border-radius: 10px;
          text-align: center;
          cursor: pointer;
          text-decoration: none;
          font-size: 16px;
        }
      }
    }
  }

  .coffer {
    height: 500px;
    width: 100%;

    div {
      height: 100%;
      background: url("../assets/images/a.webp") no-repeat center;
      background-size: cover;
      filter: brightness(80%);
    }
  }

  // 预订
  .reserve {
    position: relative;
    width: 35%;
    margin: 80px auto;

    h3 {
      width: 100%;
      position: absolute;
      font-size: 50px;
      text-align: center;
      z-index: 100;
      top: -150px;
      left: 50%;
      transform: translate(-50%, -50%);
      color: #fff;
    }

    .el-form {
      display: flex;
      width: 100%;
      flex-wrap: wrap;

      .el-form-item {
        width: 50%;
      }

      .el-date-editor {
        width: 100%;
      }
    }

    .el-button {
      margin-left: 50%;
      transform: translate(-50%);
      font-size: 20px;
      width: 200px;
      height: 50px;
      border: none;
      background-color: #ffa848;
      margin-bottom: 30px;
    }
  }
}
</style>