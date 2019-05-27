async function getDescription() {
    // Todo server api
    console.log("Hello!")
    const data = {
        title: "제대로",
        requestPrice: 500000,
        imgUrl: 'https://freshome.com/wp-content/uploads/2017/09/interior-design-living-1.jpg',
        requestList: [
            { 
                title: '벽은 하얀 벽지가 좋아요',
                imgUrl: 'https://static-s.aa-cdn.net/img/gp/20600011662178/6e_rtwiWDMWPa_Uh79nUWPFM2wKuxDadx_W7-PywgWG9dTw4Ur2PtC0HPn_tr0MrdEg=s300?v=1'}
        ]
    }
    return data;
}

export default { getDescription };