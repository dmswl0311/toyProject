<template>
  <div class="home">
    <!-- <div class="navi">navi</div> -->
    <div>{{ contents }}</div>
  </div>
</template>

<script>
import { local } from "@/global/api";

var contents;
export default {
  name: "CertDetailView",
  components: {},
  data() {
    return {
      itemId: this.$route.params.id,
      contents: this.contents,
    };
  },
  methods: {
    getData(id) {
      this.$axios
        .get(local + "/open-api/cert-info?jmcd=" + id)
        .then((res) => {
          this.contents = res.data[0].contents;
          console.log(contents);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {
    this.getData(this.itemId);
  },
};
</script>

<style scoped></style>
