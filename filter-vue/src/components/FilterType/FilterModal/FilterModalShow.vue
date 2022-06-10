<template>
  <div>
    <router-link class="return" to="/homepage">HomePage</router-link>
    <img src="../../../media/imags/modalshow.jpg" class="background" />
    <div class="self-card card-size">
      <a-input class="search" v-model="SearchData"></a-input>
      <div
        class="card-item"
        v-if="'Stepped-Impedance Filter'.includes(SearchData)"
      >
        <div class="card-title">Stepped-Impedance Filter</div>
        <img
          @click="showPicture('SIF.png')"
          class="modal"
          src="../../../media/imags/SIF.png"
        />
        <div class="introduce">Low-pass</div>
      </div>
      <div class="card-item" v-if="'Stub Filter'.includes(SearchData)">
        <div class="card-title">Stub Filter</div>
        <img
          @click="showPicture('STB.png')"
          class="modal"
          src="../../../media/imags/STB.png"
        />
        <div class="introduce">Low-pass</div>
      </div>
      <div
        class="card-item"
        v-if="'Parallel Coupled Line Filter'.includes(SearchData)"
      >
        <div class="card-title">Parallel Coupled Line Filter</div>
        <img
          @click="showPicture('PCF.png')"
          class="modal"
          src="../../../media/imags/PCF.png"
        />
        <div class="introduce">Band-pass</div>
      </div>
    </div>
    <div @click="modalshow = false" class="pup-back" v-if="modalshow">
      <div class="self-pup">
        <img :src="require(`@/assets/${imgAdd}`)" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FilterModalShow",
  watch: {
    SearchData: function (val) {
      console.log(val);
    },
  },
  data() {
    return {
      SearchData: "",
      imgAdd: "",
      modalshow: false,
    };
  },
  methods: {
    showPicture(img) {
      console.log(img);
      this.imgAdd = img;
      this.modalshow = true;
    },
  },
};
</script>
<style>
.search {
  width: 50%;
  margin-left: 230px;
}
/*滚动条样式*/
.self-card::-webkit-scrollbar {
  width: 6px;
}
.self-card::-webkit-scrollbar-thumb {
  border-radius: 20px;
  -webkit-box-shadow: inset 0 0 5px rgba(82, 195, 240, 0.2);
  background: rgba(15, 152, 243, 0.2);
}
.self-card::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  border-radius: 20px;
  background: rgba(0, 0, 0, 0.1);
}
</style>
<style scoped>
.card-size {
  overflow: auto;
  padding: 20px;
  width: 1000px;
  height: 800px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  background-color: rgba(255, 255, 255, 0.7);
}
.card-item {
  padding: 20px;
  border: 1px solid #ccc;
  margin: 0 auto;
  margin-top: 30px;
  height: 200px;
  width: 60%;

  border-radius: 10px;
}
.card-item > img {
  cursor: pointer;
}
.card-title {
  text-align: center;
  font-size: 15px;
  color: rgb(111, 183, 231);
}
.modal {
  width: 140px;
  height: 140px;
  border-radius: 10px;
}
.introduce {
  text-align: center;
  font-size: 15px;
  color: rgb(111, 183, 231);
  margin-top: -100px;
}
.return {
  position: absolute;
  top: 5%;
  right: 5%;
  transform: translateX(-50%);
  font-size: 20px;
  color: rgb(83, 127, 221);
}
</style>