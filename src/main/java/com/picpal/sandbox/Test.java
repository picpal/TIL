//package com.picpal.sandbox;
//
//public class Test {
//
//    public LData doCancel(LData transReqCc) throws Exception {
//
//        logger.println("◆ STEP:4 신용카드 취소 Bean 처리.");
//
//        LData result = new LData();
//        ResultCardCancel cancelResult = new ResultCardCancel();
//        result = (LData) transReqCc.clone();
//        result.set("returnSys", "PG");
//        boolean sendAble = false;
//        boolean isPartCancel = false;
//        boolean isAcquReq = false;
//        boolean isTimeout = false;
//        long remainAmtToDB = 0; 		// 거래잔액
//        long remainSupplyAmtToDB = 0; 	// 공급가 잔액
//        long remainGoodsAmtToDB = 0; 	// 부가세 잔액
//        long remainSvsAmtToDB = 0; 		// 봉사료 잔액
//        long remainTaxfreeAmtToDB = 0; 	// 면세금액 잔액
//        long remainPointAmtToDB = 0; 	// 포인트금액 잔액
//        long cancelCnt = 0;
//        String stateCd = "";
//        String ccStatus = "";
//        String ccDt = "";
//        String ccTm = "";
//        String cancelResultCode = "";
//        String cancelResultMsg = "";
//        String acquCo = "";
//        String appCo = "";
//        long goodsAmt = 0;
//        long supplyAmt = 0;
//        long goodsVat = 0;
//        long svsAmt = 0;
//        long taxfreeAmt = 0;
//        long pointAmt = 0;		//원거래 포인트 금액
//        long ccPointAmt = 0;	//취소 포인트 금액
//        String checkRealCc = ""; // 체크카드 즉시취소 여부
//        long tmpCupDeposit = 0; // 1회용 컵 보증금 잔액
//
//        try {
//            ntxManager.nestedBegin();
//            boolean check = false;
//            LData toCancelInfo = new LData();
//            // otid 를 입력받은 tid 로 디폴트 셋팅
//            transReqCc.set("otid", transReqCc.getString("tid"));
//            logger.println("Before TransReqCc", transReqCc);
//
//            logger.println("◆ STEP:4-1 취소가능여부 체크.");
//            check = payCheckCPbc.checkCc(transReqCc, result);
//
//            logger.println("After TransReqCc", transReqCc);
//
//            check = isCreditCard(transReqCc, check, result);
//
//            if (check) {
//
//                toCancelInfo = getCardPayData(transReqCc);
//
//
//                if (!toCancelInfo.isEmpty()) {
//
//                    transReqCc.set("app_no", toCancelInfo.getString("card_app_no"));
//                    transReqCc.set("app_dt", toCancelInfo.getString("app_dt"));
//                    transReqCc.set("app_tm", toCancelInfo.getString("app_tm"));
//                    transReqCc.set("instmnt_mon", toCancelInfo.getString("instmnt_mon"));
//                    transReqCc.set("card_expire_dt", toCancelInfo.getString("card_expire_dt"));
//                    transReqCc.set("card_no", toCancelInfo.getString("card_no"));
//                    transReqCc.set("term_no", toCancelInfo.getString("term_no"));
//                    transReqCc.set("terminal_no", toCancelInfo.getString("term_no"));
//                    transReqCc.set("auth_cl", toCancelInfo.getString("auth_cl"));
//                    transReqCc.set("van_cd", toCancelInfo.getString("van_cd"));
//                    transReqCc.set("auth_type", toCancelInfo.getString("auth_type"));
//                    transReqCc.set("kvp_tsno", toCancelInfo.getString("kvp_tsno"));
//                    transReqCc.set("kvp_auth_no", toCancelInfo.getString("kvp_auth_no"));
//                    transReqCc.set("fn_no", toCancelInfo.getString("fn_no"));
//                    transReqCc.set("ord_cp", toCancelInfo.getString("ord_cp"));
//                    transReqCc.set("ord_email", toCancelInfo.getString("ord_email"));
//                    transReqCc.set("ord_nm", toCancelInfo.getString("ord_nm"));
//                    transReqCc.set("moid", toCancelInfo.getString("moid"));
//                    transReqCc.set("non_interest_cl", toCancelInfo.getString("non_interest_cl"));
//                    transReqCc.set("goods_nm", toCancelInfo.getString("goods_nm"));
//                    transReqCc.set("subid", toCancelInfo.getString("subid"));
//                    transReqCc.set("msg_trace_no", toCancelInfo.getString("msg_trace_no"));
//                    transReqCc.set("point_cl", toCancelInfo.getString("point_cl"));
//                    transReqCc.set("paymentId", toCancelInfo.getString("co_tid"));
//                    transReqCc.set("card_cc_part_cl", toCancelInfo.getString("cc_part_cl")); // 부분취소 사용여부
//                    transReqCc.set("point_cc_part_cl", toCancelInfo.getString("point_cc_part_cl")); // 2020.08.19 포인트 부분취소 가능여부 (0:불가능, 1:가능)
//                    transReqCc.set("currency_type", toCancelInfo.getString("currency_type")); // 거래화폐종류(WON,KRW,KWN:원화,USD:달러)
//                    transReqCc.set("mall_reserved", toCancelInfo.getString("mall_reserved")); // 상점예약필드
//                    transReqCc.set("agent_tid", StringUtils.defaultString(toCancelInfo.getString("agent_tid"), "")); // 중계기관거래번호
//                    transReqCc.set("acqu_mth", toCancelInfo.getString("acqu_mth")); // 매입방법
//                    transReqCc.set("card_cl", toCancelInfo.getString("card_cl")); // 카드구분(0:일반,1:체크 2: 기프트)
//                    transReqCc.set("join_type", toCancelInfo.getString("join_type")); // 직가맹/대행  구분('0':중계,'1':대행)
//                    transReqCc.set("submall_co_no",toCancelInfo.getString("submall_co_no"));
//                    transReqCc.set("otc_code",toCancelInfo.getString("otc_code"));
//                    transReqCc.set("service_code",toCancelInfo.getString("service_code"));
//
//                    acquCo = toCancelInfo.getString("acqu_co");
//                    appCo = toCancelInfo.getString("app_co");
//                    goodsAmt = toCancelInfo.getLong("goods_amt");
//                    supplyAmt = toCancelInfo.getLong("supp_amt");
//                    goodsVat = toCancelInfo.getLong("goods_vat");
//                    svsAmt = toCancelInfo.getLong("svs_amt");
//                    taxfreeAmt = toCancelInfo.getLong("taxfree_amt");
//                    pointAmt = toCancelInfo.getLong("point_amt");
//                    checkRealCc = toCancelInfo.getString("chk_card_cc_yn");
//
//                    //오프라인 결제취소용
//                    String reqServerName = transReqCc.getString("svr_nm");
//                    boolean isOffline = "Y".equals(transReqCc.getString("off_yn"));
//                    boolean isEmptyChipData = StringUtils.isEmpty(transReqCc.getString("chip_data"));
//                    boolean isAdminServer = StringUtils.equals(reqServerName, "IMS") || StringUtils.equals(reqServerName, "MMS");
//
//                    if(isAdminServer) {
//                        transReqCc.set("chip_data",toCancelInfo.getString("chip_data"));
//                        transReqCc.set("enc_data",toCancelInfo.getString("enc_data"));
//                    }else if( isOffline && isEmptyChipData ){
//                        logger.println("Mobis 단말기 최종거래취소! ");
//                        transReqCc.set("chip_data",toCancelInfo.getString("chip_data"));
//                        transReqCc.set("enc_data",toCancelInfo.getString("enc_data"));
//                    }else if( isOffline && !StringUtil.equals(transReqCc.getString("masking_card_no"), toCancelInfo.getString("masking_card_no"))){
//                        //단말기에 입력한 카드가 승인카드와 다른경우 : 후취소 일 때 필수 체크 필요! (전취소는 밴사에서도 체크됨)
//                        check = false;
//                        result.set("returnCd", "2P62"); // 승인 카드번호 불일치
//                    }
//
//                    transReqCc.set("mobis_pay_type",toCancelInfo.getString("mobis_pay_type"));
//                    transReqCc.set("m_cstmr_id",toCancelInfo.getString("m_cstmr_id"));
//                    transReqCc.set("mobis_discount_amt",toCancelInfo.getString("mobis_discount_amt"));
//
//                    transReqCc.set("service_code",toCancelInfo.getString("service_code"));
//                    transReqCc.set("factoring_type",toCancelInfo.getString("factoring_type"));
//                    transReqCc.set("otc_type",toCancelInfo.getString("otc_type"));
//                    transReqCc.set("tmp_cup_deposit",toCancelInfo.getLong("tmp_cup_deposit"));
//
//                    String ccProtectYn = toCancelInfo.getString("cc_prvn_yn");
//                    if (StringUtils.equals(ccProtectYn, "Y")) {
//                        check = false;
//                        result.set("returnCd", "2P08"); // 취소 불가능 거래
//                        LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                    }
//
//                    logger.println("신용카드 거래원장 조회 결과 TransReqCc", transReqCc);
//
//                } else {
//                    check = false;
//                    result.set("returnCd", "2P07"); // 취소 해당거래 없음
//                    LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                }
//            }
//
//            // 프로모션 여부 체크
//
//            if (check) {
//                logger.println("◆ STEP:4-3-1 카드 취소를 위한 프로모션 거래원장(tb_promotion_trans) 조회.");
//
//                check = getPromotionCancelInfo(transReqCc, result, toCancelInfo);
//
//                if (check) {
//                    if (StringUtils.isBlank(transReqCc.getString("promotion_cd"))) {
//                        logger.println("◆ STEP:4-3-2 프로모션 적용거래 아니므로 일반취소 수행.");
//                        transReqCc.set("promotion_yn", false);
//                    } else {
//                        transReqCc.set("promotion_yn", true);
//                        if (StringUtils.equals("1", transReqCc.getString("partialCancelCode"))) {
//                            // 부분취소시엔 프로모션 할인금액을 제외한 금액만큼만 취소 가능
//                            logger.printf("◆ STEP:4-3-2 프로모션 적용거래 / 부분취소 / 취소대상금액: {%d}", goodsAmt);
//                        } else  {
//                            // 전체취소시엔 프로모션 할인금액을 더한 원거래금액 모두 취소 가능
//                            logger.printf("◆ STEP:4-3-2 프로모션 적용거래 / 전체취소 / 취소대상금액: {%d} + {%d} = {%d}", goodsAmt, transReqCc.getLong("discount_amt"), (goodsAmt + transReqCc.getLong("discount_amt")));
//                            goodsAmt = transReqCc.getLong("ot_amt"); // 원거래금액 설정 (실카드결제금액 + 프로모션할인금액)
//                        }
//                    }
//                }
//            }
//
//            // 가맹점별 취소체크 사항
//            if (check) {
//                logger.printf("◆ STEP:4-3-2 가맹점별 취소체크 사항.(mid:{%s})", transReqCc.getString("mid"));
//                check = checkMallCancel(transReqCc, result, toCancelInfo);
//            }
//            // 전체취소와 부분취소 취소금액 체크
//            if (check) {
//
//                // 부분취소
//                if (StringUtils.equals("1", transReqCc.getString("partialCancelCode"))) {
//
//                    logger.println("◆ STEP:4-4 부분취소 체크.");
//
//                    // 세이브결제 부분 취소 불가
//                    if (StringUtils.equals(CommonConstants.CARD_POINT_2, transReqCc.getString("point_cl"))) {
//                        check = false;
//                        result.set("returnCd", "2P23"); // 세이브결제 부분취소 불가.
//                        LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                    }
//
//                    // 프로모션 결제 부분취소 불가 거래 Check
//                    // partcancel_yn -> N:부분취소불가, Y:부분취소가능
//                    if (transReqCc.getBoolean("promotion_yn") && StringUtils.equals(transReqCc.getString("partcancel_yn"), "N")) {
//                        check = false;
//                        result.set("returnCd", "2P02"); // 부분취소 불가능한 프로모션 거래. 전체취소처리요망.
//                        LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");;
//                    }
//
//                    // 체크카드 부분취소 불가: 20150409 add by cbd
//                    if (StringUtils.equals(transReqCc.getString("acqu_cl"), "99") && CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl"))) {
//                        check = false;
//                        result.set("returnCd", "2P27"); // 부분취소 불가능 카드번호
//                        LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                    }
//
//                    // 1회용컵보증금 거래 부분취소 불가
//                    if (transReqCc.getLong("tmp_cup_deposit") > 0) {
//                        check = false;
//                        result.set("returnCd", "2P63"); //  1회용컵보증금 거래 부분취소 불가
//                        LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                    }
//
//                    LData cancelBox = new LData();
//                    cancelBox.set("otid", transReqCc.getString("tid"));
//
//                    logger.printf("◆ STEP:4-4-1 부분취소 히스토리(tb_part_cc_pay_hist) 마지막 거래건 조회(OTID:{%s}).",transReqCc.getString("tid"));
//                    LData partCancelHist = tbPartCcPayHistEbc.retrievePartCancelhistLastByOtid(cancelBox);
//
//                    // 잔여금액이랑 비교
//                    if (!partCancelHist.isEmpty()) { // 기존에 부분취소 1회 이상 발생했을 경우
//
//                        long remainAmt = partCancelHist.getLong("tr_remain_amt"); 				// 거래잔액
//                        long remainSupplyAmt = partCancelHist.getLong("supp_remain_amt"); 		// 공급가 잔액
//                        long remainGoodsVat = partCancelHist.getLong("remain_vat"); 			// 부가세 잔액
//                        long remainSvsAmt = partCancelHist.getLong("remain_svs_amt"); 			// 봉사료 잔액
//                        long remainTaxfreeAmt = partCancelHist.getLong("remain_taxfree_amt"); 	// 면세금액 잔액
//                        long remainPointAmt = partCancelHist.getLong("remain_point_amt"); 		// 포인트 잔액
//
//                        cancelCnt = partCancelHist.getLong("cc_seq") + 1;
//
//                        remainAmtToDB = remainAmt - transReqCc.getLong("cancelAmt"); // 취소후 잔여금액
//
//                        logger.printf("◆ STEP:4-4-2 이전에 부분취소거래건의 금액을 제외한 잔여금액({%d})으로 취소가능한지 금액({%d}) 체크.", remainAmt, transReqCc.getLong("cancelAmt"));
//
//                        // 거래잔액 체크
//                        if (remainAmtToDB < 0) {
//                            // 프로모션 거래
//                            if (transReqCc.getBoolean("promotion_yn"))  {
//                                result.set("returnCd", "2P03"); // 프로모션적용거래. 취소금액이 취소가능금액보다 큼.
//                            } else {
//                                result.set("returnCd", "2P21"); // 취소금액이 취소가능금액보다 큼.
//                            }
//                            check = false;
//                            LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                        }
//
//                        // 가맹점에서 잔액 확인 시 체크
//                        if (check) {
//                            if (transReqCc.getLong("check_remain_amt") > 0) {
//                                logger.printf("◆ STEP:4-4-3 잔액 확인용 금액({%d})과 실제 잔액({%d}) 체크.", transReqCc.getLong("check_remain_amt"), remainAmt + transReqCc.getLong("discount_amt"));
//                                // 원거래 금액으로 체크
//                                if (remainAmt + transReqCc.getLong("discount_amt") != transReqCc .getLong("check_remain_amt")) {
//                                    check = false;
//                                    result.set("returnCd", "2P35"); // 잔액 불일치
//                                    LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                                }
//                            }
//                        }
//
//                        if (check) {
//                            logger.println("◆ STEP:4-4-4 부가가치세 표시가 지정금액의 경우 공급가잔액,부가세잔액,봉사료잔액 체크.");
//                            if(StringUtil.equals(CommonConstants.MERCHANT_VAT_MARK_0, transReqCc.getString("vat_mark"))){
//                                remainSupplyAmtToDB = Math.round((double)(remainAmtToDB / 1.1)); 		// 취소후 공급가 잔액
//                                remainGoodsAmtToDB = remainAmtToDB - remainSupplyAmtToDB; 					// 취소후 부가세 잔액
//                            }else{
//                                remainSupplyAmtToDB = remainSupplyAmt - transReqCc.getLong("supp_amt"); 		// 취소후 공급가 잔액
//                                remainGoodsAmtToDB = remainGoodsVat - transReqCc.getLong("goods_vat");			// 취소후 부가세 잔액
//                            }
//                            remainSvsAmtToDB = remainSvsAmt - transReqCc.getLong("svs_amt"); 				// 취소후 봉사료 잔액
//                            remainTaxfreeAmtToDB = remainTaxfreeAmt - transReqCc.getLong("taxfree_amt"); 	// 취소후 면세금액 잔액
//
//                            if(remainAmtToDB > 0 && remainPointAmt > 0){
//                                ccPointAmt = (long) Math.floor(remainPointAmt * ((double)transReqCc.getLong("cancelAmt") / remainAmt));	//취소 포인트 금액
//                                remainPointAmtToDB = remainPointAmt - ccPointAmt;	//취소후 포인트 잔액
//                            }else{
//                                ccPointAmt = remainPointAmt;
//                                remainPointAmtToDB = 0;
//                            }
//
//                            transReqCc.setLong("point_amt", ccPointAmt);
//
//                            logger.printf("remainSupplyAmt: " +remainSupplyAmt);
//                            logger.printf("remainGoodsVat: " +remainGoodsVat);
//                            logger.printf("remainSvsAmt: " +remainSvsAmt);
//                            logger.printf("remainTaxfreeAmt: " +remainTaxfreeAmt);
//                            logger.printf("remainPointAmt: " +remainPointAmt);
//
//                            // 공급가 잔액 체크
//                            if (check && remainSupplyAmtToDB < 0) {
//                                LLog.err.printf("부분취소 공급가액:{%d}, 공급가 잔액:{%d}", transReqCc.getLong("supp_amt"), remainSupplyAmt);
//                                check = false;
//                                result.set("returnCd", "2P31"); // 요청하신 공급가액이 취소가능한 공급가액을 초과 오류
//                                LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                            }
//
//                            // 부가세 잔액 체크
//                            if (check && remainGoodsAmtToDB < 0) {
//                                LLog.err.printf("부분취소 부가세:{%d}, 부가세 잔액:{%d}", transReqCc.getLong("goods_vat"), remainGoodsVat);
//                                check = false;
//                                result.set("returnCd", "2P32"); // 요청하신 부가가치세가 취소가능한 부가가치세를 초과 오류
//                                LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                            }
//
//                            // 봉사료 잔액 체크
//                            if (check && remainSvsAmtToDB < 0) {
//                                LLog.err.printf("부분취소 봉사료:{%d}, 봉사료 잔액:{%d}", transReqCc.getLong("svs_amt"), remainSvsAmt);
//                                check = false;
//                                result.set("returnCd", "2P33"); // 요청하신 봉사료가 취소가능한 봉사료 초과 오류
//                                LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                            }
//                            // 면세금액 잔액 체크
//                            if (check && remainTaxfreeAmtToDB < 0) {
//                                LLog.err.printf("부분취소 면세금액:{%d}, 면세금액 잔액:{%d}", transReqCc.getLong("taxfree_amt"), remainTaxfreeAmt);
//                                check = false;
//                                result.set("returnCd", "2P60"); // 요청하신 면세금액이 취소가능한 면세금액 초과 오류
//                                LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                            }
//                        }
//                    }
//                    // 원거래금액이랑 비교
//                    else {
//                        // 첫 부분취소 일 경우
//                        remainAmtToDB = goodsAmt - transReqCc.getLong("cancelAmt"); // 취소후 잔여금액
//                        logger.printf("◆ STEP:4-4-2 원거래금액({%d})으로 취소가능한지 금액({%d}) 체크.", goodsAmt, transReqCc.getLong("cancelAmt"));
//                        cancelCnt = 1;
//
//                        if (goodsAmt < transReqCc.getLong("cancelAmt")) {
//                            // 프로모션 거래
//                            if (transReqCc.getBoolean("promotion_yn")) {
//                                result.set("returnCd", "2P04"); // 프로모션적용거래. 취소금액이 취소가능금액보다 큼.
//                            } else {
//                                result.set("returnCd", "2P22"); // 부분취소 불가능금액. 전체취소 이용바람.
//                            }
//                            check = false;
//                            LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                        } else if (goodsAmt == transReqCc.getLong("cancelAmt")) {
//                            // 프로모션외 거래
//                            if (!transReqCc.getBoolean("promotion_yn")) {
//                                result.set("returnCd", "2P22"); // 부분취소 불가능금액. 전체취소 이용바람.
//                                check = false;
//                                LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                            }
//                        }
//
//                        // 가맹점에서 잔액 확인 시 체크
//                        if (check) {
//                            if (transReqCc.getLong("check_remain_amt") > 0) {
//                                logger.printf("◆ STEP:4-4-3 잔액 확인용 금액({%d})과 실제 잔액({%d}) 체크.", transReqCc.getLong("check_remain_amt"), goodsAmt + transReqCc.getLong("discount_amt"));
//                                // 원거래 금액으로 체크
//                                if (goodsAmt + transReqCc.getLong("discount_amt") != transReqCc.getLong("check_remain_amt")) {
//                                    check = false;
//                                    result.set("returnCd", "2P35"); // 잔액 불일치
//                                    LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                                }
//                            }
//                        }
//
//                        if (check && remainAmtToDB > 0) {
//                            logger.println("◆ STEP:4-4-4 부가가치세 표시가 지정금액의 경우 공급가액,부가세,봉사료 체크.");
//                            if(StringUtil.equals(CommonConstants.MERCHANT_VAT_MARK_0, transReqCc.getString("vat_mark"))){
//                                remainSupplyAmtToDB = Math.round((double)(remainAmtToDB / 1.1)); 	// 취소후 공급가 잔액
//                                remainGoodsAmtToDB = remainAmtToDB - remainSupplyAmtToDB; 				// 취소후 부가세 잔액
//                            }else{
//                                remainSupplyAmtToDB = supplyAmt - transReqCc.getLong("supp_amt"); 		// 취소후 공급가 잔액
//                                remainGoodsAmtToDB = goodsVat - transReqCc.getLong("goods_vat");		// 취소후 부가세 잔액
//                            }
//                            remainSvsAmtToDB = svsAmt - transReqCc.getLong("svs_amt"); 				// 취소후 봉사료 잔액
//                            remainTaxfreeAmtToDB = taxfreeAmt - transReqCc.getLong("taxfree_amt"); 	// 취소후 면세금액 잔액
//
//                            if(remainAmtToDB > 0 && pointAmt > 0){
//                                ccPointAmt =  (long) Math.floor(pointAmt * ((double)transReqCc.getLong("cancelAmt") / goodsAmt));	//취소 포인트 금액
//                                remainPointAmtToDB = pointAmt - ccPointAmt;	//취소후 포인트 잔액
//                            }else{
//                                ccPointAmt = pointAmt;
//                                remainPointAmtToDB = 0;
//                            }
//
//                            transReqCc.setLong("point_amt", ccPointAmt);
//
//                            logger.printf("supplyAmt: " +supplyAmt);
//                            logger.printf("goodsVat: " +goodsVat);
//                            logger.printf("svsAmt: " +svsAmt);
//                            logger.printf("taxfreeAmt: " +taxfreeAmt);
//                            logger.printf("pointAmt: " +pointAmt);
//
//                            // 공급가액 체크
//                            if (check && remainSupplyAmtToDB < 0) {
//                                LLog.err.printf("부분취소 공급가액:{%d}, 원거래 공급가액:{%d}", transReqCc.getLong("supp_amt"), supplyAmt);
//                                check = false;
//                                result.set("returnCd", "2P31"); // 요청하신 공급가액이 취소가능한 공급가액을 초과 오류
//                                LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                            }
//                            // 부가세 체크
//                            if (check && remainGoodsAmtToDB < 0) {
//                                LLog.err.printf("부분취소 부가세:{%d}, 원거래 부가세:{%d}", transReqCc.getLong("goods_vat"), goodsVat);
//                                check = false;
//                                result.set("returnCd", "2P32"); // 요청하신 부가가치세가 취소가능한 부가가치세를 초과 오류
//                                LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                            }
//                            // 봉사료 체크
//                            if (check && remainSvsAmtToDB < 0) {
//                                LLog.err.printf("부분취소 봉사료:{%d}, 원거래 봉사료:{%d}", transReqCc.getLong("svs_amt"), svsAmt);
//                                check = false;
//                                result.set("returnCd", "2P33"); // 요청하신 봉사료가 취소가능한 봉사료 초과 오류
//                                LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                            }
//                            // 면세금액 잔액 체크
//                            if (check && remainTaxfreeAmtToDB < 0) {
//                                LLog.err.printf("부분취소 면세금액:{%d}, 면세금액 잔액:{%d}", transReqCc.getLong("taxfree_amt"), taxfreeAmt);
//                                check = false;
//                                result.set("returnCd", "2P60"); // 요청하신 면세금액이 취소가능한 면세금액 초과 오류
//                                LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                            }
//                        }
//                    }
//
//                    // BIN 테이블 조회
//                    if (check) {
//                        LData input = new LData();
//                        input.set("bin", transReqCc.getString("card_no"));
//                        LData companyData = companyBinEbc.retrieveLstCompanyBinByCardBin(input);
//                        LMultiData list = (LMultiData) companyData.get("list");
//                        if (!list.isEmpty() && list.getDataCount() > 0) {
//                            LData companyBin = list.getLData(0);
//                            transReqCc.set("card_kind_code", companyBin.getString("card_kind_code"));
//                        }
//                        logger.printf("◆ STEP:4-4-5 카드번호로 BIN 테이블 조회(card_kind_code:{%s}).", transReqCc.getString("card_kind_code"));
//
//                        // 부분취소 가능여부 체크
//                        String ccPartCl = getCcPartCl(transReqCc.getString("merchant_cc_part_cl"), transReqCc.getString("card_cc_part_cl"), acquCo, appCo, transReqCc.getString("card_kind_code"), transReqCc.getString("point_cl"), transReqCc.getString("point_cc_part_cl"));
//                        logger.printf("◆ STEP:4-4-6 부분취소 가능여부 체크(mid:{%s}, cc_part_cl:{%s}).", transReqCc.getString("mid"), ccPartCl);
//
//                        if (!StringUtils.equals(ccPartCl, "1")) {
//                            check = false;
//                            // 포인트구분 (0:일반,1:포인트,2:세이브포인트,3:코인)
//                            if (StringUtils.equals(transReqCc.getString("point_cl"), CommonConstants.CARD_POINT_0)) {
//                                if (StringUtils.equals(transReqCc.getString("card_cc_part_cl"), "0")) {
//                                    result.set("returnCd", "2P28"); // 부분취소 불가능 카드사 가맹점번호
//                                } else {
//                                    result.set("returnCd", "2P27"); // 부분취소 불가능 카드번호
//                                }
//                            } else {
//                                result.set("returnCd", "2P34"); // 부분취소 불가능 복합결제
//                            }
//                            LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                        }
//                    }
//                }
//                // 전체취소
//                else {
//
//                    logger.println("◆ STEP:4-4 전체취소 체크.");
//
//                    // 부분취소 이력 있으면 전체취소 불가
//                    if (check) {
//                        logger.printf("◆ STEP:4-4-1 부분취소 이력(tb_part_cc_pay_hist)이 있는지 체크(OTID:{%s}).", transReqCc.getString("tid"));
//                        LData cancelBox = new LData();
//                        cancelBox.set("otid", transReqCc.getString("tid"));
//                        LData partCancelHist = tbPartCcPayHistEbc.retrieveLstPartCancelHistoryByOtid(cancelBox);
//                        LMultiData partCancelHistList = (LMultiData) partCancelHist.get("list");
//                        if (!partCancelHistList.isEmpty() && partCancelHistList.getDataCount() > 0) {
//                            check = false;
//                            result.set("returnCd", "2P20"); // 전체금액취소 불가.
//                            LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                        }
//                    }
//
//                    // 취소 금액 체크
//                    if (check) {
//
//                        logger.printf("◆ STEP:4-4-2 원승인금액({%d})과 취소금액({%d}) 체크.", goodsAmt, transReqCc.getLong("cancelAmt"));
//
//                        if (goodsAmt != transReqCc.getLong("cancelAmt")) {
//                            check = false;
//                            result.set("returnCd", "2P06"); // 취소 금액 불일치
//                            LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                        } else {
//                            transReqCc.set("cancelAmt", goodsAmt - transReqCc.getLong("discount_amt")); // 실 카드금액만 취소하기 위해 설정
//                        }
//                    }
//
//                    // 가맹점에서 잔액 확인 시 체크
//                    if (check) {
//                        if (transReqCc.getLong("check_remain_amt") > 0) {
//                            logger.printf("◆ STEP:4-4-3 잔액 확인용 금액({%d})과 실제 잔액({%d}) 체크.", transReqCc.getLong("check_remain_amt"), goodsAmt);
//                            if (goodsAmt != transReqCc.getLong("check_remain_amt")) {
//                                check = false;
//                                result.set("returnCd", "2P35"); // 잔액 불일치
//                                LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                            }
//                        }
//                    }
//                }
//            }
//
//            // 부분취소 허용 여부 체크
//            if (check) {
//
//                // 해외결제는 부분취소 불가
//                if (StringUtils.equals(transReqCc.getString("currency_type"), CommonConstants.CURRENCY_TYPE_USD) && StringUtils.equals(transReqCc.getString("partialCancelCode"), "1")) {
//                    LLog.err.printf("◆ STEP:4-4-6 해외결제(currency_type:{%s})는 부분취소 불가.", transReqCc.getString("currency_type"));
//                    check = false;
//                    result.set("returnCd", "2P27"); // 부분취소 불가능 카드번호
//                    LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                }
//            }
//
//            // 오픈마켓 신용승인 20150312 add by cbd
//            if (check) {
//                if (StringUtils.equals(transReqCc.getString("om_trans_yn"), "Y")) {
//                    logger.printf("◆ STEP:4-5-5 오픈마켓 JSON정보 유효성 체크.(Om_trans_yn:{%s})", transReqCc.getString("om_trans_yn"));
//                    check = checkJSonCancelInfo(transReqCc, result);
//                }
//            }
//
//            // TID에 대한 매입정보 조회 후 전취소,후취소 셋팅
//            if (check) {
//
//                // tb_trans_history
//                // 부분취소
//                if (StringUtils.equals("1", transReqCc.getString("partialCancelCode"))) {
//
//                    logger.printf("◆ STEP:4-6-1 [일반결제+부분취소] 부분취소 매입정보(tb_trans_history) 조회(TID:{%s}).", transReqCc.getString("tid"));
//                    LData cancelTransHistoryData = new LData();
//                    cancelTransHistoryData.set("tid", transReqCc.getString("tid"));
//                    LData cancelTransHistory = tbTransHistoryEbc.retrieveToCancelTransHistory(cancelTransHistoryData);
//
//                    if (!cancelTransHistory.isEmpty()) {
//                        transReqCc.set("fn_cd", cancelTransHistory.getString("fn_cd"));
//                        transReqCc.set("preCancel", false); // 후취소
//                        stateCd = CommonConstants.TRANS_STATE_CD_2; // 후취소
//                        ccStatus = CommonConstants.TRANS_HISTORY_CC_STATUS_CANCEL_AFT; // 환불(매입후)
//                        result.set("stateCd", stateCd);
//                        transReqCc.set("date_flag", cancelTransHistory.getString("date_flag"));
//                        // 취소 전문 전송 안함.
//                        sendAble = false;
//                        cancelResultCode = "0000"; // 취소 결과 코드
//                        cancelResultMsg = "후취소"; // 취소 결과 메시지
//                        logger.printf("◆ STEP:4-6-2 부분취소-후취소 처리 셋팅(PreCancel:{%s}).", transReqCc.getString("preCancel"));
//                    } else {
//
//                        check = false;
//                        logger.printf("transReqCC.isPreCancel(): ", transReqCc.getString("preCancel"));
//                        LLog.err.printf("tb_trans_history 테이블에 거래내역 없음(TID:{%s}).", transReqCc.getString("tid"));
//                        result.set("returnCd", "2P07"); // 취소 해당거래 없음
//                        LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                    }
//                }
//                // 전체취소
//                else {
//                    logger.printf("◆ STEP:4-6-1 전체취소 매입정보(tb_trans_history) 조회(TID:{%s}).", transReqCc.getString("tid"));
//                    LData cancelTransHistoryData = new LData();
//                    cancelTransHistoryData.set("tid", transReqCc.getString("tid"));
//                    LData cancelTransHistory = tbTransHistoryEbc.retrieveToCancelTransHistory(cancelTransHistoryData);
//
//                    if (!cancelTransHistory.isEmpty()) {
//                        transReqCc.set("fn_cd", cancelTransHistory.getString("fn_cd"));
//                        String historyStatus = cancelTransHistory.getString("status");
//                        logger.printf("transHistory status:{%s},fn_cd:{%s}", historyStatus, transReqCc.getString("fn_cd"));
//                        // logger.printf("skyfour2 date_flag:{}",
//                        // cancelTransHistory.getString("date_flag"));
//                        // logger.printf("skyfour2 card_cl:{}",
//                        // transReqCc.getString("card_cl"));
//                        // 최초 승인일로부터 현재까지의 일자수
//                        transReqCc.set("date_flag", cancelTransHistory.getString("date_flag"));
//
//                        // 전취소,후취소 셋팅: 20150409 add by cbd
//                        // 미매입
//                        if (StringUtils.equals(transReqCc.getString("acqu_cl"), "99")) {
//                            transReqCc.set("preCancel", true); // 전취소
//                            logger.printf("◆ STEP:4-6-2 미매입-전체취소-전취소 처리 셋팅(PreCancel:{%s}).", transReqCc.getString("preCancel"));
//                        }
//                        // 미매입 외
//                        else {
//                            // 매입방식: 자동 && 매입유형: 당일(기아CPO 모델)
//                            // tb_trans_history.STATUS 승인이면
//                            // 전취소
//                            // tb_trans_history.STATUS 승인이 아니면
//                            // 후취소
//
//                            // 수동매입 외
//                            if (!StringUtils.equals(transReqCc.getString("acqu_cl"), "6")) {
//                                LData appAcquDayData = new LData();
//                                appAcquDayData.set("tid", transReqCc.getString("tid"));
//                                LData appAcquDayResult = commonEbc.retrieveAppAcquDay(appAcquDayData); // 매입예상일
//                                String appAcquDay = appAcquDayResult.getString("app_acqu_day");
//                                if (appAcquDay == null || StringUtils.equals(appAcquDay, "0")) {
//                                    check = false;
//                                    result.set("returnCd", "2P08"); // 취소 불가능 거래
//                                    LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                                } else if (Integer.parseInt(appAcquDay) > Integer.parseInt(transReqCc.getString("cc_req_dt"))) {
//                                    // tb_trans_history.status 승인이 아니면 후취소
//                                    // 후취소 처리
//                                    // 그외는 전취소
//                                    if (StringUtils.equals(transReqCc.getString("service_code"), CommonConstants.SERVICE_CODE_KIACPO)
//                                            && !cancelTransHistory.getString("status").equals(CommonConstants.TRANS_HISTORY_STATUS_00)) {
//                                        transReqCc.set("preCancel", false); // 후취소
//                                        logger.printf("◆ STEP:4-6-2 수동매입외, 승인외상태 -전체취소-후취소 처리 셋팅(PreCancel:{%s}).", transReqCc.getString("preCancel"));
//                                    } else {
//                                        transReqCc.set("preCancel", true); // 전취소
//                                        logger.printf("◆ STEP:4-6-2 수동매입외, 승인상태-전체취소-전취소 처리 셋팅(PreCancel:{%s}).", transReqCc.getString("preCancel"));
//                                    }
//                                } else {
//                                    // tb_trans_history.status 승인이면
//                                    // 전취소 처리
//                                    // 그외는 후취소
//                                    if (StringUtils.equals(transReqCc.getString("service_code"), CommonConstants.SERVICE_CODE_KIACPO)
//                                            && cancelTransHistory.getString("status").equals(CommonConstants.TRANS_HISTORY_STATUS_00)) {
//                                        transReqCc.set("preCancel", true); // 전취소
//                                        logger.printf("◆ STEP:4-6-2 수동매입외, 승인상태 -전체취소-전취소 처리 셋팅(PreCancel:{%s}).", transReqCc.getString("preCancel"));
//                                    } else {
//                                        transReqCc.set("preCancel", false); // 후취소
//                                        logger.printf("◆ STEP:4-6-2 수동매입외 승인외상태 -전체취소-후취소 처리 셋팅(PreCancel:{%s}).", transReqCc.getString("preCancel"));
//                                    }
//                                }
//                            }
//                            // 수동매입(+취소포함)
//                            else {
//                                LData acquReqCntData = new LData();
//                                acquReqCntData.set("tid", transReqCc.getString("tid"));
//                                LData acquReqCntBox = tbAcquReqEbc.retrieveTbAcquReq(acquReqCntData);
//                                long acquReqCnt = acquReqCntBox.getLong("seq");
//
//                                // 매입요청 테이블(tb_acqu_req) 등록된 상태
//                                if (acquReqCnt > 0) {
//                                    transReqCc.set("preCancel", false); // 후취소
//                                    logger.printf("◆ STEP:4-6-2 수동매입-전체취소-후취소 처리 셋팅(PreCancel:{%s}).", transReqCc.getString("preCancel"));
//                                } else {
//
//
//                                    logger.printf("◆ STEP:4-6-2 수동매입-전체취소-후취소 처리 셋팅(precancel_code:{%s}).", transReqCc.getString("precancel_code"));
//
//                                    if(StringUtils.equals("1", transReqCc.getString("precancel_code"))){
//                                        transReqCc.set("preCancel", false); // 후취소
//                                        logger.printf("◆ STEP:4-6-2 수동매입-전체취소-후취소 처리 셋팅(PreCancel:{%s}).", transReqCc.getString("preCancel"));
//                                    }else{
//                                        transReqCc.set("preCancel", true); // 전취소
//                                        logger.printf("◆ STEP:4-6-2 수동매입-전체취소-전취소 처리 셋팅(PreCancel:{%s}).", transReqCc.getString("preCancel"));
//                                    }
//                                }
//                            }
//                        }
//
//                        if (check) {
//                            // 매입요청 상태에서 취소한 건을 매입을 보내지 않도록 매입 요청 테이블 상태를 변경하기
//                            // 위한 용도
//                            // 변경된 전/후취소 기준으로 인해 tb_trans_history를 사용하지 않음
//                            // '전취소' 일 경우에 tb_acqu_req.status 를 update처리함
//                            // if
//                            // (historyStatus.equals(CommonConstants.TRANS_HISTORY_STATUS_INIT2))
//                            // {
//                            // isAcquReq = true; // 매입요청상태
//                            // }
//
//                            // 전취소
//                            if (transReqCc.getBoolean("preCancel")) {
//                                stateCd = CommonConstants.TRANS_STATE_CD_1; // 전취소
//                                ccStatus = CommonConstants.TRANS_HISTORY_CC_STATUS_CANCEL_PRE; // 취소(매입전)
//                                result.set("stateCd", stateCd);
//                                // 취소 전문 전송 요청.
//                                sendAble = true;
//
//                                // 전취소의 경우에만 tb_acqu_req.status를 '2'로 update하기
//                                // 위해 true로 설정함.
//                                isAcquReq = true;
//                            }
//                            // 후취소
//                            else {
//                                stateCd = CommonConstants.TRANS_STATE_CD_2; // 후취소
//                                ccStatus = CommonConstants.TRANS_HISTORY_CC_STATUS_CANCEL_AFT; // 환불(매입후)
//                                result.set("stateCd", stateCd);
//
//                                // 거래된 카드가 체크카드 && 거래일시가 90이전 인경우 취소전문 요청
//                                // 2022.06.24 비씨 직승인일 경우 매입취소 처리
//                                // 2023.01.09 현대, 국민, 하나, 농협 예외처리 추가
//                                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl"))
//                                        && 90 >= cancelTransHistory.getInt("date_flag")
//                                        && !StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_BC)
//                                        && !StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_HYUNDAI)
//                                        && !StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KB)
//                                        && !StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_HANA)
//                                        && !StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_NONGHYUP)
//                                        && !StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_WOORI) // TEST_LSH 추가 여부 확인 필요
//                                ) {
//                                    sendAble = true;
//                                    transReqCc.set("preCancel", true); // 전취소
//                                    // 체크카드 즉시취소 시 CHECK_REAL_CC = Y
//                                    checkRealCc = CommonConstants.CHECK_REAL_CC_Y;
//                                    logger.println("◆ 체크카드 즉시취소 CHECK_REAL_CC : Y");
//                                } else {
//                                    // 취소 전문 전송 안함.
//                                    sendAble = false;
//                                    cancelResultCode = "0000"; // 취소 결과 코드
//                                }
//
//                                cancelResultMsg = "후취소"; // 취소 결과 메시지
//                            }
//                        }
//
//                    } else {
//                        check = false;
//                        logger.printf("transReqCC.getBoolean(preCancel): {%s}", transReqCc.getString("preCancel"));
//                        LLog.err.printf("tb_trans_history 테이블에 거래내역 없음(TID:{%s}).", transReqCc.getString("tid"));
//                        result.set("returnCd", "2P07"); // 취소 해당거래 없음
//                        LLog.err.println("ErrCode:{"+result.getString("returnCd")+"},ErrMsg:{"+MessageService.getInstance().getMessage(result.getString("returnCd"))+"}, TID:{"+transReqCc.getString("tid")+"}");
//                    }
//                }
//            }
//
//            // 취소한도 체크
//            if (check) {
//                logger.println("◆ STEP:4-5 취소한도 체크.");
//                check = confirmCancelAmtOrCancelCount(transReqCc, result, check);
//                if (!check) {
//                    sendAble = false;
//                    cancelResultCode = "";
//                    cancelResultMsg = "";
//                }
//            }
//
//            // 거래취소요청(tb_pay_req_cc_log) 저장
//            if (check) {
//                // 부분취소 (매입후취소로 처리되므로 별도 채번 안함)
//                if (StringUtils.equals("1", transReqCc.getString("partialCancelCode"))) {
//                    // 원승인거래 TID
//                    transReqCc.set("otid", transReqCc.getString("tid"));
//                    // 부분취소용 TID 생성
//                    if(StringUtils.equals(transReqCc.getString("service_code"), CommonConstants.SERVICE_CODE_KOR_AIR)){
//                        String canMsgTraceNo ="";
//                        LData seqValData = new LData();
//                        seqValData = commonEbc.retrieveSequenceNum19(seqValData);
//                        canMsgTraceNo =seqValData.getString("seq");
//                        transReqCc.set("tid", KeyUtils.genPartialKorAirTID(transReqCc.getString("mid"), CommonConstants.SVC_CD_CARD, transReqCc.getString("tid").substring(12, 14),canMsgTraceNo));
//                    }else{
//                        transReqCc.set("tid", KeyUtils.genPartialTID(transReqCc.getString("mid"), CommonConstants.SVC_CD_CARD, transReqCc.getString("tid").substring(12, 14)));
//
//                    }
//                    logger.printf("◆ STEP:4-8 부분취소용 TID생성. TID={%s}, OTID={%s}", transReqCc.getString("tid"), transReqCc.getString("otid"));
//                    result.set("part_cc_tid", transReqCc.getString("tid")); // 부분취소용 TID 셋팅	//2019.11.04 주석처리
//                    isPartCancel = true;
//                }
//                // 전체취소
//                else {
//                    // SEQ획득
//                    // NICE 밴
//                    int seqVal = 0;
//                    String canMsgTraceNo = "";
//                    LData seqValData = new LData();
//                    if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_NICE)) {
//                        seqValData = commonEbc.retrieveSequenceNum(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyyyMMdd((Date) transReqCc.get("now")) + BzUtils.get12FixSequence(seqValData.getInt("seq"));
//                    }
//                    // KIS 밴
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KIS)) {
//                        seqValData = commonEbc.retrieveSequenceNum2(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyyyMMdd((Date) transReqCc.get("now")) + BzUtils.get12FixSequence(seqValData.getInt("seq"));
//                    }
//                    // 20130930 KOVAN add by Yoon
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KOVAN)) {
//                        seqValData = commonEbc.retrieveSequenceNum3(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyyyMMdd((Date) transReqCc.get("now")) + BzUtils.get12FixSequence(seqValData.getInt("seq"));
//                    }
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_SMARTRO)) {
//                        seqValData = commonEbc.retrieveSequenceNum4(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyMMdd((Date) transReqCc.get("now")) + BzUtils.get6FixSequence(seqValData.getInt("seq"));
//                    }
//                    // 신한카드 직승인
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_SHINHAN)) {
//                        seqValData = commonEbc.retrieveSequenceNum5(seqValData);
//                        canMsgTraceNo = CommonConstants.HANDLE_AGENCY_CD_SHINHAN + StringUtils.right(TimeUtils.getyyMMdd((Date) transReqCc.get("now")), 2) + BzUtils.get6FixSequence(seqValData.getInt("seq"));
//                    }
//                    // KIWOOM 밴
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KIWOOM)) {
//                        seqValData = commonEbc.retrieveSequenceNum8(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyyyMMdd((Date) transReqCc.get("now")) + BzUtils.get12FixSequence(seqValData.getInt("seq"));
//                    }
//                    // 현대카드 직승인
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_HYUNDAI)) {
//                        seqValData = commonEbc.retrieveSequenceNum9(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyMMdd((Date) transReqCc.get("now")) + BzUtils.get6FixSequence(seqValData.getInt("seq"));
//                    }
//                    // koces
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KOCES)) {
//                        seqValData = commonEbc.retrieveSequenceNum10(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyMMdd((Date) transReqCc.get("now")) + BzUtils.get6FixSequence(seqValData.getInt("seq"));
//                    }
//                    // fdk
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_FDK)) {
//                        seqValData = commonEbc.retrieveSequenceNum11(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyMMdd((Date) transReqCc.get("now")) + BzUtils.get6FixSequence(seqValData.getInt("seq"));
//                    }
//                    // 롯데카드 직승인
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_LOTTE)) {
//                        seqValData = commonEbc.retrieveSequenceNum12(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyMMdd((Date) transReqCc.get("now")) + BzUtils.get6FixSequence(seqValData.getInt("seq"));
//                    }
//                    // 국민카드 직승인
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KB)) {
//                        seqValData = commonEbc.retrieveSequenceNum13(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyMMdd((Date) transReqCc.get("now")) + BzUtils.get6FixSequence(seqValData.getInt("seq"));
//                    }
//                    // 삼성카드 직승인
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_SAMSUNG)) {
//                        seqValData = commonEbc.retrieveSequenceNum14(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyMMdd((Date) transReqCc.get("now")) + BzUtils.get6FixSequence(seqValData.getInt("seq"));
//                    }
//                    // 비씨카드 직승인
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_BC)) {
//                        seqValData = commonEbc.retrieveSequenceNum16(seqValData);
//                        canMsgTraceNo = StringUtils.right(CommonConstants.HANDLE_AGENCY_CD_BC, 3) +StringUtils.right(TimeUtils.getyyMMdd((Date) transReqCc.get("now")), 2)+"1"+BzUtils.get6FixSequence(seqVal);
//                    }
//                    // 하나카드 직승인
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_HANA)) {
//                        seqValData = commonEbc.retrieveSequenceNum17(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyMMdd((Date) transReqCc.get("now")) + BzUtils.get6FixSequence(seqValData.getInt("seq"));
//                    }
//                    // 농협카드 직승인
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_NONGHYUP)) {
//                        seqValData = commonEbc.retrieveSequenceNum18(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyMMdd((Date) transReqCc.get("now")) + BzUtils.get6FixSequence(seqValData.getInt("seq"));
//                    }
//                    // 우리카드 직승인
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_WOORI)) {
//                        seqValData = commonEbc.retrieveSeqCardWoori(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyMMdd((Date) transReqCc.get("now")) + BzUtils.get6FixSequence(seqValData.getInt("seq"));
//                    }
//                    // BWC 밴
//                    else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_BWC)) {
//                        seqValData = commonEbc.retrieveSequenceNum15(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyyyMMdd((Date) transReqCc.get("now")) + BzUtils.get12FixSequence(seqValData.getInt("seq"));
//                    }else{
//                        seqValData = commonEbc.retrieveSequenceNum(seqValData);
//                        canMsgTraceNo = TimeUtils.getyyMMdd((Date) transReqCc.get("now")) + BzUtils.get6FixSequence(seqValData.getInt("seq"));
//                    }
//                    seqVal = seqValData.getInt("seq");
//                    transReqCc.set("sequence", seqVal);
//                    transReqCc.set("can_msg_trace_no", canMsgTraceNo);
//                    logger.printf("◆ STEP:4-8 거래전문 시퀀스(tb_sequence) 조회(van_cd={%s}, seq={%d}, can_msg_trace_no={%s}).", transReqCc.getString("van_cd"), seqVal, canMsgTraceNo);
//                    transReqCc.set("otid", transReqCc.getString("tid"));
//                }
//            }
//            ntxManager.nestedCommit();
//        } catch (LException ex) {
//            throw ex;
//        } finally {
//            try {
//                ntxManager.nestedRelease();
//            } catch (Exception eex) {
//                LLog.err.printf("Error: {%s}", eex.getStackTrace().toString());
//            }
//        }
//
//        // VAN 전문 송,수신
//        if (sendAble) {
//            logger.printf("◆ STEP:4-9-1 VAN 객체(VAN:{%s}) 획득.", transReqCc.getString("van_cd"));
//
//            // VAN 클래스 획득 (NICE,KIS,VP...)
//            VanCard vanCard = VanCardFactory.getVanCard(transReqCc.getString("van_cd"));
//            // ISP 거래
//
//            if (StringUtils.equals(transReqCc.getString("auth_type"), CommonConstants.VAN_AUTH_TYPE_ISP)) {
//                // 망상취소가 아닌 경우 'false'
//                cancelResult = vanCard.sendCancelISP(transReqCc, false);
//            }
//            // BluePay 간편결제 승인취소 bwc118
//            else if (StringUtils.equals(transReqCc.getString("auth_type"), CommonConstants.VAN_AUTH_TYPE_BLUEPAY)) {
//                // 망상취소가 아닌 경우 'false'
//                cancelResult = vanCard.sendCancelBluepay(transReqCc, false);
//            }
//            // CarPay 간편결제 auth_type(08)
//            else if (StringUtils.equals(transReqCc.getString("auth_type"), CommonConstants.VAN_AUTH_TYPE_CARPAY)) {
//                // 망상취소가 아닌 경우 'false'
//                cancelResult = vanCard.sendCancelCarpay(transReqCc, false);
//            }
//            // 바로페이
//            else if (StringUtils.equals(transReqCc.getString("auth_type"), CommonConstants.VAN_AUTH_TYPE_PINPAY)) {
//                // 망상취소가 아닌 경우 'false'
//                cancelResult = vanCard.sendCancelPinpay(transReqCc, false);
//            }
//            // 네이버페이
//            else if (StringUtils.equals(transReqCc.getString("auth_type"), CommonConstants.VAN_AUTH_TYPE_NAVERPAY_CARD)) {
//                // 망상취소가 아닌 경우 'false'
//                cancelResult = vanCard.sendCancelNaverpay(transReqCc, false);
//            }
//            // Key-In, Visa3D 거래, 단말기 거래('ETC' 이외의 거래)
//            else {
//                logger.printf("[Cancel Key-In, Visa3D 거래, 단말기 거래('ETC' 이외의 거래)]");
//                logger.println("transReqCc ", transReqCc);
//                if(StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_FDK) && transReqCc.getString("off_yn").equals("Y")) {
//                    cancelResult = vanCard.sendOffCancelVisaKeyin(transReqCc, false); // Key-IN 오프라인 취소
//                } else {
//                    cancelResult = vanCard.sendCancelVisaKeyin(transReqCc, false);
//                }
//            }
//
//            logger.println("◆ STEP:4-9-1 완료");
//
//            logger.println("◆ STEP:4-9-2 코드 테이블(tb_code)에서 VAN사 응답메시지 조회.");
//            processCancelVanResultMessage(transReqCc, cancelResult);
//            cancelResultCode = cancelResult.getVanResult();
//            cancelResultMsg = cancelResult.getVanResultMsg();
//            logger.printf("◆ STEP:4-9-3 VAN 응답코드:{%s},응답메시지:{%s},TID:{%s}", cancelResultCode, cancelResultMsg,
//                    transReqCc.getString("tid"));
//            // 밴 기취소 응답시 취소성공으로 처리
//            // NICE밴 : 6003
//            if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_NICE)) {
//                if (StringUtils.equals(cancelResultCode, "6003")) { // 취소거절이미취소된거래
//                    cancelResultCode = "0000"; // 취소 성공
//                }
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl"))
//                        && 90 >= transReqCc.getInt("date_flag") && StringUtils.equals(cancelResultCode, "6004")) // 6004:취소거절이미매입된거래
//                {
//                    cancelResultCode = "0000"; // 취소 성공
//                }
//            }
//            // SMARTRO : 6003
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_SMARTRO)) {
//                if (StringUtils.equals(cancelResultCode, "0004")) { // 0004	취소거절 (이미취소된거래)
//                    cancelResultCode = "0000"; // 취소 성공
//                }
//
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청 && 기매입거래
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl"))
//                        && 90 >= transReqCc.getInt("date_flag") && StringUtils.equals(cancelResultCode, "0005")) // 0005 취소불가거래 (기 매입된 거래)
//
//                {
//                    cancelResultCode = "0000"; // 취소 성공
//                }
//            }
//            // 20130930 KOVAN add by Yoon
//            // KOVAN: 25 (기취소거래)
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KOVAN)) {
//                if (StringUtils.equals(cancelResultCode, "25")) { // 취소거절이미취소된거래
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl"))
//                        && 90 >= transReqCc.getInt("date_flag") && StringUtils.equals(cancelResultCode, "36")) // 36:이미매입된거래
//                {
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//            }
//            // KIS밴 : 5006,7573,2123,5023,8816,8123,0118,02J7
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KIS)) {
//                if (StringUtils.equals(cancelResultCode, "5006") || // 기취소전문
//                        StringUtils.equals(cancelResultCode, "7573") || // 기취소된건
//                        StringUtils.equals(cancelResultCode, "2123") || // 기취소거래
//                        StringUtils.equals(cancelResultCode, "5023") || // 기취소거래
//                        StringUtils.equals(cancelResultCode, "8816") || // 기취소거래
//                        StringUtils.equals(cancelResultCode, "8123") || // 취소불가기취소됨
//                        StringUtils.equals(cancelResultCode, "0118") || // 원승인기취소
//                        StringUtils.equals(cancelResultCode, "02J7")) { // 승인취소불가기취소
//                    cancelResultCode = "0000"; // 취소 성공
//                }
//            }
//            // 신한 직승인
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_SHINHAN)) {
//                if (StringUtils.equals(cancelResultCode, "59")) // 기취소건
//                {
//                    cancelResultCode = "0000"; // 취소 성공
//                }
//
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl"))
//                        && 90 >= transReqCc.getInt("date_flag") && StringUtils.equals(cancelResultCode, "58")) // 58:기매입
//                {
//                    cancelResultCode = "0000"; // 취소 성공
//                }
//            }
//            // KIWOOM 밴
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KIWOOM)) {
//                if (StringUtils.equals(cancelResultCode, "25")) { // 취소거절이미취소된거래
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl")) && 90 >= Integer.parseInt(transReqCc.getString("date_flag")) && StringUtils.equals(cancelResultCode, "36")) // 36:이미매입된거래
//                {
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//            }
//            // 현대카드 직승인
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_HYUNDAI)) {
//                if (StringUtils.equals(cancelResultCode, "45")) { // 45:이미취소된거래
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl")) && 90 >= Integer.parseInt(transReqCc.getString("date_flag")) && StringUtils.equals(cancelResultCode, "44")) // 44:이미매입된거래
//                {
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//            }
//            // KOCES
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KOCES)) {
//                if (StringUtils.equals(cancelResultCode, "D3") || StringUtils.equals(cancelResultCode, "0025")) { // 0025:이미취소된거래, D3:기취소거래
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청  && 기매입거래
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl")) && 90 >= Integer.parseInt(transReqCc.getString("date_flag")) && (StringUtils.equals(cancelResultCode, "D4") || StringUtils.equals(cancelResultCode, "0022"))) // D4:이미매입된거래
//                {
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//            }
//            // FDK
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_FDK)) {
//                if (StringUtils.equals(cancelResultCode, "D3") || StringUtils.equals(cancelResultCode, "5001") || StringUtils.equals(cancelResultCode, "0025")) { // 5001:기취소건, D3:기취소거래, 0025:기취소거래
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청  && 기매입거래
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl")) && 90 >= Integer.parseInt(transReqCc.getString("date_flag")) && (StringUtils.equals(cancelResultCode, "D4") || StringUtils.equals(cancelResultCode, "5003"))) // D4:이미매입된거래, 5003:기매입거래
//                {
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//            }
//            // 롯데카드 직승인
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_LOTTE)) {
//                if (StringUtils.equals(cancelResultCode, "90")) { // 90: 취소-기취소
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청  && 기매입거래
//                // 95: 취소-기매입
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl")) && 90 >= Integer.parseInt(transReqCc.getString("date_flag")) && (StringUtils.equals(cancelResultCode, "95")))
//                {
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//            }
//            // 국민카드 직승인
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KB)) {
//                if (StringUtils.equals(cancelResultCode, "J7")) { // J7:기취소 거래
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청
//                // J2: 승인취소 불가/기매입
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl")) && 90 >= Integer.parseInt(transReqCc.getString("date_flag")) && (StringUtils.equals(cancelResultCode, "J2")))
//                {
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//            }
//            // 삼성카드 직승인
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_SAMSUNG)) {
//                if (StringUtils.equals(cancelResultCode, "65")) { // J7:기취소 거래
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청
//                // J2: 승인취소 불가/기매입
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl")) && 90 >= Integer.parseInt(transReqCc.getString("date_flag")) && (StringUtils.equals(cancelResultCode, "35")))
//                {
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//            }
//            // 비씨카드 직승인
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_BC)) {
//                if (StringUtils.equals(cancelResultCode, "1037") || StringUtils.equals(cancelResultCode, "1054") || StringUtils.equals(cancelResultCode, "18") || StringUtils.equals(cancelResultCode, "98") ) { // 기취소 거래
//                    cancelResultCode = "0000"; // 취소 성공
//                }
//
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청
//                // J2: 승인취소 불가/기매입
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl")) && 90 >= Integer.parseInt(transReqCc.getString("date_flag")) && (StringUtils.equals(cancelResultCode, "I8")))
//                {
//                    cancelResultCode = "0000"; // 취소 성공
//                }
//
//            }
//            // 하나카드 직승인
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_HANA)) {
//                if (StringUtils.equals(cancelResultCode, "K2")) { // 기취소 거래
//                    cancelResultCode = "0000"; // 취소 성공
//                }
//
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청
//                // J2: 승인취소 불가/기매입
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl")) && 90 >= Integer.parseInt(transReqCc.getString("date_flag")) && (StringUtils.equals(cancelResultCode, "K2")))
//                {
//                    cancelResultCode = "0000"; // 취소 성공
//                }
//
//            }
//            // 농협카드 직승인 : 기취소 코드 존재하지 않음 : 210608
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_NONGHYUP)) {
//                if (StringUtils.equals(cancelResultCode, "44")) { // 기취소 거래
//                    cancelResultCode = "0000"; // 취소 성공
//                }
//
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청
//                // J2: 승인취소 불가/기매입
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl")) && 90 >= Integer.parseInt(transReqCc.getString("date_flag")) && (StringUtils.equals(cancelResultCode, "45")))
//                {
//                    cancelResultCode = "0000"; // 취소 성공
//                }
//
//            }
//            // 우리카드 직승인
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_WOORI)) {
//                if (StringUtils.equals(cancelResultCode, "91")) { // 기취소 거래
//                    cancelResultCode = "0000"; // 취소 성공
//                }
//
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청
//                // 95: 승인취소 불가/기매입
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl")) && 90 >= Integer.parseInt(transReqCc.getString("date_flag")) && (StringUtils.equals(cancelResultCode, "95")))
//                {
//                    cancelResultCode = "0000"; // 취소 성공
//                }
//
//            }
//            // BWC 밴
//            else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_BWC)) {
//                if (StringUtils.equals(cancelResultCode, "A9") || StringUtils.equals(cancelResultCode, "L6") || StringUtils.equals(cancelResultCode, "1811") || StringUtils.equals(cancelResultCode, "3602")) { // 1811:기취소건, A9:기취소건, 3602:기취소거래, L6:기취소거래
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//                // 거래된 카드가 체크카드 && 원거래일자로부터 90일 이내의 취소전문 요청  && 기매입거래
//                if (CommonConstants.CARD_CL_1.equals(transReqCc.getString("card_cl")) && 90 >= Integer.parseInt(transReqCc.getString("date_flag")) && (StringUtils.equals(cancelResultCode, "L5") || StringUtils.equals(cancelResultCode, "3601"))) // L5:이미매입된거래, 3601:기매입거래
//                {
//                    cancelResultCode = "00"; // 취소 성공
//                }
//
//            }
//            // 취소 Timeout 재취소 처리
//            // 응답전문을 받았으나 AnyLink에서 돌아온 오류 응답코드이거나 카드사 Timeout 응답코드인 경우
//            if (StringUtils.length(cancelResult.getVanResult()) == 2
//                    && (StringUtils.equals(cancelResult.getVanResult(), "L0")
//                    || StringUtils.equals(cancelResult.getVanResult(), "L1"))) {
//                isTimeout = true;
//            } else if (StringUtils.length(cancelResult.getVanResult()) == 4
//                    && (StringUtils.equals(cancelResult.getVanResult(), "L110")
//                    || StringUtils.equals(cancelResult.getVanResult(), "L111"))) {
//                isTimeout = true;
//            } else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_NICE)
//                    && StringUtils.equals(cancelResult.getVanResult(), "0010")) {
//                isTimeout = true;
//            } else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_SMARTRO)
//                    && StringUtils.equals(cancelResult.getVanResult(), "0010")) {
//                isTimeout = true;
//            } else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KOVAN)
//                    && (StringUtils.equals(cancelResult.getVanResult(), "10")
//                    || StringUtils.equals(cancelResult.getVanResult(), "0110"))) {
//                isTimeout = true;
//            } else if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KIWOOM)
//                    && (StringUtils.equals(cancelResult.getVanResult(), "10")
//                    ||  StringUtils.equals(cancelResult.getVanResult(), "0110"))) {
//                isTimeout = true;
//            }
//
//            try {
//                if (isTimeout) {
//                    if (StringUtils.equals(transReqCc.getString("join_type"), "1")) {// 직대행구분 (0:자체, 1:대표)
//                        logger.printf("◆ STEP:4-9-3-1 응답({%s}) 전문수신 실패 시 배치 재취소 처리.", cancelResult.getVanResult());
//                        processTimeoutPayCancel(transReqCc, cancelResult);
//                        if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KOVAN)
//                                || StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KIWOOM)) {
//                            cancelResultCode = "00"; // 취소성공
//                        } else {
//                            cancelResultCode = "0000"; // 취소성공
//                        }
//                        cancelResultMsg = "정상완료";
//                    }
//                } else {
//                    logger.println("◆ STEP:4-9-3-2 응답전문을 정상적으로 받았으므로 재취소처리->SKIP.");
//                }
//            } catch (Exception e) {
//                LLog.err.printf("Error: {%s}", e.getStackTrace().toString());
//                // 망상취소에서 Exception 이 발생되었더라도 무시하고 아래 실패로직으로 처리될 수 있도록 함.
//            }
//
//            // Kovan, Smartro, kiwoom의 경우에만 Check
//            if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KOVAN)
//                    || StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_SMARTRO)
//                    || StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KIWOOM)
//                    || StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_HYUNDAI)
//                    || StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KOCES)
//                    || StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_FDK)
//                    || StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_BWC)) {
//                String reqTransUniqueNo = "";
//
//                if (StringUtils.equals(transReqCc.getString("van_cd"), CommonConstants.VAN_KOVAN)
//                        && !StringUtils.equals(transReqCc.getString("auth_type"), CommonConstants.VAN_AUTH_TYPE_ISP)) // Kovan and Not ISP
//                {
//                    reqTransUniqueNo = TimeUtils.getyyyyMMdd((Date) transReqCc.get("now"))
//                            + BzUtils.get12FixSequence(transReqCc.getInt("sequence"));
//                } else // Smartro or Kovan(ISP)
//                {
//                    reqTransUniqueNo = transReqCc.getString("msg_trace_no");
//                }
//
////				// 응답전문을 받았으나 AnyLink에서 돌아온 오류 응답코드일 경우 거래고유번호 비고 제외
////				if (StringUtils.length(cancelResult.getVanResult()) == 2
////						&& (StringUtils.equals(cancelResult.getVanResult(), "L0")
////								|| StringUtils.equals(cancelResult.getVanResult(), "L1"))) {
////					cancelResult.setResEdiNo(reqTransUniqueNo);
////				} else if (StringUtils.length(cancelResult.getVanResult()) == 4
////						&& (StringUtils.equals(cancelResult.getVanResult(), "L110")
////								|| StringUtils.equals(cancelResult.getVanResult(), "L111"))) {
////					cancelResult.setResEdiNo(reqTransUniqueNo);
////				}
//
//                // Van 요청, 응답 시의 거래고유번호 비교
//                if (!StringUtils.equals(reqTransUniqueNo, cancelResult.getResEdiNo())) {
//                    logger.println("◆ STEP:4-9-4 Van 취소연동 후 요청,응답 시 거래고유번호 비교 : 불일치 - 메일전송");
//                    logger.println("◆ STEP:4-9-4 거래고유번호(요청 / 응답) : " + reqTransUniqueNo + " / " + cancelResult.getResEdiNo());
//                    // 메일전송
//                    StringBuffer mailMsg = new StringBuffer(500);
//                    // 메일 내용 설정
//                    mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 거래고유번호 체크 시 불일치 발생 (취소 시)");
//                    mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ TID : ").append(transReqCc.getString("tid"));
//                    mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 취소일자 : ").append(TimeUtils.getyyyyMMdd((Date) transReqCc.get("now")));
//                    mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 취소시간 : ").append(TimeUtils.getHHmmss((Date) transReqCc.get("now")));
//                    mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 취소금액 : ").append(transReqCc.getLong("cancelAmt"));
//                    mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ EDI_NO : ").append(transReqCc.getInt("sequence"));
//                    mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 취소서버명 : ").append(transReqCc.getString("svr_nm"));
//                    mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 원천사 결과코드 : ").append(cancelResultCode);
//                    mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 원천사 결과메시지 : ").append(cancelResultMsg);
//                    mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 결과코드 : ").append(getPgCode(transReqCc.getString("van_cd"), cancelResultCode, result));
//                    mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 결과메시지 : ").append(getPgMsg(transReqCc.getString("van_cd"), cancelResultMsg, result));
//                    mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 거래고유번호(요청 / 응답) : " + reqTransUniqueNo + " / " + cancelResult.getResEdiNo());
//
//                    NotiUtils.notify("N013", "Van 요청,응답 거래고유번호 불일치:" + reqTransUniqueNo + "/" +
//                                    cancelResult.getResEdiNo(), "거래고유번호 체크 시 불일치 발생 (취소 시)", mailMsg.toString(),
//                            transReqCc.getString("mid"));
//                } else {
//                    logger.println("◆ STEP:4-9-4 Van 취소연동 후 요청,응답 시 거래고유번호 비교 : 일치");
//                }
//            }
//
//        } else {
//            logger.printf("◆ STEP:4-9 VAN사(VAN:{%s}) 전문 전송 -> SKIP.", transReqCc.getString("van_cd"));
//        }
//
//        try {
//
//            ntxManager.nestedBegin();
//
//            ccDt = TimeUtils.getyyyyMMdd((Date) transReqCc.get("now"));
//            ccTm = TimeUtils.getHHmmss((Date) transReqCc.get("now"));
//
//            //후취소 요청인경우 취소일자/시간 설정
//            if("1".equals(transReqCc.getString("precancel_code"))){
//                if(StringUtil.isNotEmpty(transReqCc.getString("cancel_dt")) && StringUtil.isNotEmpty(transReqCc.getString("cancel_tm"))){
//                    ccDt = transReqCc.getString("cancel_dt");
//                    ccTm = transReqCc.getString("cancel_tm");
//                }
//            }
//
//            result.set("returnSys", "PG");
//            result.set("resultCode", cancelResultCode);
//            result.set("resultMsg", cancelResultMsg);
//            result.set("cancelAmt", transReqCc.getLong("cancelAmt")); // 취소금액
//            result.set("remainAmt", remainAmtToDB); // 취소후 잔여금액
//            result.set("remainPointAmt", remainPointAmtToDB); // 취소후 잔여포인트금액
//            result.set("currencyType", transReqCc.getString("currency_type")); // 거래화폐종류(WON,KRW,KWN:원화,USD:달러)
//
//            result.set("ServiceCode", transReqCc.getString("service_code")); // 서비스코드
//
//            // 2017.01.10. O2O 결제 취소 결과 추가
//            if(StringUtils.isNotEmpty(transReqCc.getString("off_yn")) && "Y".equals(transReqCc.getString("off_yn"))) {
//                result.setString("encData", StringUtils.defaultString(cancelResult.getEncData()));
//                result.setString("chipData", StringUtils.defaultString(cancelResult.getChipData()));
//            }
//
//            // KOVAN은 "00"임
//            // 취소 성공
//            if (StringUtils.equals(cancelResultCode, "0000") || StringUtils.equals(cancelResultCode, "00")) {
//
//                result.set("returnCd", "2001"); // 취소 성공
//                result.set("cancelDate", ccDt);
//                result.set("cancelTime", ccTm);
//                result.set("moid", transReqCc.getString("moid"));
//
//                // 프로모션 거래 시 추가 파라미터 설정
//                if (transReqCc.getBoolean("promotion_yn")) {
//                    // Promotion 정보
//                    result.set("promotionCd", transReqCc.getString("promotion_cd"));
//                    result.set("recoverYn", transReqCc.getString("recover_yn"));
//                    if (StringUtils.equals("1", transReqCc.getString("partialCancelCode"))) {
//                        // 부분취소시에는 할인금액을 넘기지 않음.
//                        result.set("discountAmt", 0);
//                    } else {
//                        result.set("discountAmt", transReqCc.getLong("discount_amt"));
//                    }
//                }
//
//                if (transReqCc.getBoolean("preCancel")) {
//                    result.set("cancelNum", cancelResult.getAppNo()); // 취소승인번호
//                }
//
//                // 카드 취소 성공시에만 거래취소요청(tb_pay_req_cc_log) 테이블에 저장
//                logger.println("◆ STEP:4-10 거래취소요청(tb_pay_req_cc_log) 저장.");
//                transReqCc.set("rslt_cd", result.getString("resultCode"));
//                transReqCc.set("rslt_msg", result.getString("resultMsg"));
//                tbPayReqCcLogEbc.insertTransReqCc(transReqCc);
//                //DB입력실패case : 카드 취소 거래 결과처리 실패 -- 취소요청이력 저장 실패
//
//                // 부분취소
//                if (isPartCancel) {
//
//                    logger.println("◆ STEP:4-11 부분취소 응답클래스 셋팅.");
//                    logger.printf("◆ STEP:4-11-1 원거래승인TID({%s})로 통합거래내역(tb_pay_info) 조회.", transReqCc.getString("otid"));
//                    LData transData = new LData();
//                    transData.set("tid", transReqCc.getString("otid"));
//                    LData trans = tbPayInfoEbc.retrieveTransByTid(transData);
//
//                    trans.set("tid",transReqCc.getString("tid"));
//                    trans.set("state_cd",CommonConstants.TRANS_STATE_CD_2);
//                    trans.set("cc_dt",ccDt);
//                    trans.set("cc_tm",ccTm);
//                    trans.set("url_inform_cd",CommonConstants.TRANS_URL_SND_NONE);
//                    trans.set("otid",transReqCc.getString("otid"));
//                    trans.set("goods_amt",transReqCc.getLong("cancelAmt"));
//                    trans.set("supp_amt",transReqCc.getLong("supp_amt"));
//                    trans.set("goods_vat",transReqCc.getLong("goods_vat"));
//                    trans.set("svs_amt",transReqCc.getLong("svs_amt"));
//                    trans.set("taxfree_amt",transReqCc.getLong("taxfree_amt"));
//                    trans.set("cart_type",transReqCc.getString("cart_type")); // 장바구니 결제 유형
//                    trans.set("service_code",transReqCc.getString("service_code"));
//                    trans.set("tmp_cup_deposit", tmpCupDeposit);	// 컵보증금 부분취소 0 고정
//                    if("1".equals(transReqCc.getString("pay_inform_cl").substring(1,2)))
//                        trans.set("cc_url_re_inform_cl", "Y");
//                    else
//                        trans.set("cc_url_re_inform_cl", "N");
//
//                    logger.printf("◆ STEP:4-11-2 부분취소TID({%s})로 통합거래내역(tb_pay_info) 저장.", transReqCc.getString("tid"));
//                    tbPayInfoEbc.insertTrans(trans);
//
//                    logger.printf("◆ STEP:4-11-3 원거래승인TID({%s})로 거래원장History(tb_trans_history) 조회.",transReqCc.getString("otid"));
//                    LData transHistoryData = new LData();
//                    LData transHistory = new LData();
//                    CommonBizCPbc.initializeTransHistory(transHistory);
//                    transHistoryData.set("tid", transReqCc.getString("otid"));
//                    transHistory = tbTransHistoryEbc.retrieveTransHistoryByTid(transHistoryData);
//                    transHistory.set("tid",transReqCc.getString("tid"));
//                    transHistory.set("state_cd",CommonConstants.TRANS_STATE_CD_2);
//                    transHistory.set("cc_status",CommonConstants.TRANS_HISTORY_CC_STATUS_CANCEL_AFT);
//                    transHistory.set("cc_dt",ccDt);
//                    transHistory.set("otid",transReqCc.getString("otid"));
//                    transHistory.set("goods_amt",transReqCc.getLong("cancelAmt"));
//                    transHistory.set("supp_amt",transReqCc.getLong("supp_amt"));
//                    transHistory.set("goods_vat",transReqCc.getLong("goods_vat"));
//                    transHistory.set("svs_amt",transReqCc.getLong("svs_amt"));
//                    transHistory.set("taxfree_amt",transReqCc.getLong("taxfree_amt"));
//                    transHistory.set("point_amt",transReqCc.getLong("point_amt"));
//                    transHistory.set("cart_type",transReqCc.getString("cart_type")); // 장바구니 결제 유형
//                    transHistory.set("service_code",transReqCc.getString("service_code"));
//                    transHistory.set("tmp_cup_deposit", tmpCupDeposit);	// 컵보증금 부분취소 0 고정
//                    tbTransHistoryEbc.insertTransHistory(transHistory);
//
//                    logger.printf("◆ STEP:4-11-5 원거래승인TID({%s})로 카드거래내역(tb_card_pay) 조회.", transReqCc.getString("otid"));
//                    LData cardTransData = new LData();
//                    LData cardTrans = new LData();
//                    CommonBizCPbc.initializeCardTrans(cardTrans);
//                    functionsCPbc.copyToCardTrans(transReqCc, cardTrans);
//
//                    cardTransData.set("tid", transReqCc.getString("otid"));
//                    cardTrans = tbCardPayEbc.retrieveCardTransByTid(cardTransData);
//                    cardTrans.set("tid",transReqCc.getString("tid"));
//                    cardTrans.set("state_cd",CommonConstants.TRANS_STATE_CD_2);
//                    cardTrans.set("cc_req_dt",transReqCc.getString("cc_req_dt"));
//                    cardTrans.set("cc_req_tm",transReqCc.getString("cc_req_tm"));
//                    cardTrans.set("cc_dt",ccDt);
//                    cardTrans.set("cc_tm",ccTm);
//                    cardTrans.set("cc_reason",transReqCc.getString("cc_reason"));
//                    cardTrans.set("cc_reason_cd",transReqCc.getString("cc_reason_cd"));
//                    cardTrans.set("cc_id",transReqCc.getString("cc_id"));
//                    cardTrans.set("cc_ip",transReqCc.getString("cc_ip"));
//                    cardTrans.set("goods_amt",transReqCc.getLong("cancelAmt"));
//                    cardTrans.set("cart_type",transReqCc.getString("cart_type")); // 장바구니
//                    // 결제 유형
//                    cardTrans.set("acqu_mth","ETC"); // 매입방법
//                    cardTrans.set("chk_card_cc_yn",checkRealCc);
//
//                    cardTrans.set("supp_amt",transReqCc.getLong("supp_amt")); 		// 공급가액
//                    cardTrans.set("goods_vat",transReqCc.getLong("goods_vat")); 	// 부가세
//                    cardTrans.set("svs_amt",transReqCc.getLong("svs_amt")); 		// 봉사료
//                    cardTrans.set("taxfree_amt",transReqCc.getLong("taxfree_amt")); // 면세금액
//                    cardTrans.set("point_amt",transReqCc.getLong("point_amt")); 	// 포인트금액
//
//                    cardTrans.set("service_code",transReqCc.getString("service_code"));
//
//                    cardTrans.set("bptid",transReqCc.getString("bptid"));// BluePay 간편결제 bwc118
//                    cardTrans.set("tmp_cup_deposit", tmpCupDeposit);	// 컵보증금 부분취소 0 고정
//                    cardTrans.set("co_tid", transReqCc.getString("paymentId"));
//
//                    /*
//                     * LData rBox = Functions.selectSettlmnt_cycle(client,
//                     * transReqCc.getString("mid"),
//                     * transReqCc.getString("svc_cd"),
//                     * transReqCc.getString("svc_prdt_cd"),
//                     * TimeUtils.getyyyyMMdd((Date) transReqCc.get("now")));
//                     * logger.println("◆ STEP: 취소 정산주기 체크.::" +transReqCc.getString("mid")+"::"+transReqCc.getString("svc_cd")+"::"+ transReqCc.getString("svc_prdt_cd"));
//                     * if(rBox != null){ logger.printf("settlmnt_cycle:{}", rBox.toString());
//                     * cardTrans.set("Settlmnt_cycle",rBox.getString("settlmnt_cycle"));//정산주기
//                     * cardTrans.set("Settlmnt_c_biz_type",rBox.getString("settlmnt_c_biz_type"));//정산주기-업무일기준
//                     * cardTrans.set("Settlmnt_c_day",rBox.getString("settlmnt_c_day"));//정산주기-지급일 }
//                     */
//
//                    logger.printf("◆ STEP:4-11-6 부분취소TID({%s})로 카드거래내역(tb_card_pay) 저장.",transReqCc.getString("tid"));
//                    tbCardPayEbc.insertCardTrans(cardTrans);
//
//                    logger.printf("◆ STEP:4-11-7 부분취소TID({%s})로 부분취소히스토리(tb_part_cc_pay_hist) 저장.",transReqCc.getString("tid"));
//                    LData partCancelHistory = new LData();
//                    CommonBizCPbc.initializePartCancelHistory(partCancelHistory);
//                    functionsCPbc.copyToPartCancelHistory(transReqCc, partCancelHistory);
//
//                    if (cancelCnt == 0) {
//                        cancelCnt = 1;
//                    }
//
//                    partCancelHistory.set("cc_seq",cancelCnt); // 차수
//                    partCancelHistory.set("tr_remain_amt",remainAmtToDB); 				// 취소후 잔여금액
//                    partCancelHistory.set("supp_remain_amt",remainSupplyAmtToDB); 		// 취소후 공급가 잔액 취소후부가세 잔액
//                    partCancelHistory.set("remain_svs_amt",remainSvsAmtToDB); 			// 취소후 봉사료 잔액
//                    partCancelHistory.set("remain_vat",remainGoodsAmtToDB); 			// 취소후 부가세 잔액
//                    partCancelHistory.set("remain_taxfree_amt",remainTaxfreeAmtToDB); 	// 취소후 면세금액 잔액
//                    partCancelHistory.set("remain_point_amt",remainPointAmtToDB); 		// 취소후 포인트 잔액
//                    tbPartCcPayHistEbc.insertPartCancelHistory(partCancelHistory);
//
//                    // 오픈마켓 부분취소 20150312 add by cbd
//                    if (StringUtils.equals(transReqCc.getString("om_trans_yn"), "Y")) {
//                        logger.printf("◆ STEP:4-11-8 부분취소TID({%s}) 매출전표 정보 원장(tb_om_slip_info) 취소상태 Update.",transReqCc.getString("tid"));
//
//                        LData omSlipInfo = new LData();
//                        CommonBizCPbc.initializeOmSlipInfo(omSlipInfo);
//                        functionsCPbc.copyToOmSlipInfo(transReqCc, omSlipInfo);
//
//                        omSlipInfo.set("tid",transReqCc.getString("otid"));
//                        omSlipInfo.set("use_yn","Y"); // 사용여부
//                        omSlipInfo.set("cancel_yn","Y"); // 취소여부
//                        omSlipInfo.set("cancel_dt",TimeUtils.getyyyyMMdd((Date) transReqCc.get("now")) + TimeUtils.getHHmmss((Date) transReqCc.get("now")));
//                        omSlipInfo.set("update_dt",omSlipInfo.getString("cancel_dt"));
//
//                        // TID 조건 & USE_YN=‘Y’ 인 Data -> CANCEL_YN=‘Y’ 로 Update
//                        tbOmSlipInfoEbc.updateOmSlipInfo(omSlipInfo);
//                        //DB입력실패case : 카드 취소 거래 결과처리 실패 -- 매출전표 취소처리 오류
//
//                        JSONParser jsonParser = new JSONParser();
//                        // JSON데이터를 넣어 JSON Object 로 만들어 준다.
//                        JSONObject jsonObject = (JSONObject) jsonParser.parse("{\"OM_SLIP_INFO\":" + transReqCc.getString("om_slip_info") + "}");
//                        // OM_SLIP_INFO의 배열을 추출
//                        JSONArray omSlipInfoArray = (JSONArray) jsonObject.get("OM_SLIP_INFO");
//
//                        for (int i = 0; i < omSlipInfoArray.size(); i++) {
//
//                            // 배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
//                            JSONObject omSlipObject = (JSONObject) omSlipInfoArray.get(i);
//                            // JSON name으로 추출
//                            omSlipInfo.set("product_id",omSlipObject.get("OM_PRODUCT_ID").toString());
//                            omSlipInfo.set("seller_id",omSlipObject.get("OM_SELLER_ID").toString());
//                            omSlipInfo.set("order_dt",omSlipObject.get("OM_ORDER_DATE").toString());
//                            omSlipInfo.set("product_type",omSlipObject.get("OM_PRODUCT_TYPE").toString());
//                            omSlipInfo.set("product_info",omSlipObject.get("OM_PRODUCT_INFO").toString());
//
//                            omSlipInfo.set("amount",Long.parseLong(omSlipObject.get("OM_AMOUNT").toString()));
//                            omSlipInfo.set("supp_amt",Long.parseLong(omSlipObject.get("OM_SUPPLY_AMT").toString()));
//                            omSlipInfo.set("vat",Long.parseLong(omSlipObject.get("OM_VAT").toString()));
//                            omSlipInfo.set("service_amt",Long.parseLong(omSlipObject.get("OM_SERVICE_AMT").toString()));
//                            omSlipInfo.set("tax_type",omSlipObject.get("OM_TAX_TYPE").toString());
//
//                            omSlipInfo.set("use_yn","N"); // 사용여부
//                            omSlipInfo.set("update_dt",TimeUtils.getyyyyMMdd((Date) transReqCc.get("now"))+ TimeUtils.getHHmmss((Date) transReqCc.get("now")));
//
//                            // TID 조건 & PRODUCT_ID 조건 & USE_YN=‘Y’ 인 Data ->
//                            // USE_YN=‘N’ 으로 Update
//                            tbOmSlipInfoEbc.updateOmSlipInfo2(omSlipInfo);
//                            //DB입력실패case : 카드 취소 거래 결과처리 실패 -- 매출전표 취소처리 오류
//
//                            LData box = new LData();
//                            box.set("tid", omSlipInfo.getString("tid"));
//                            box.set("product_id", omSlipInfo.getString("product_id"));
//                            LData omSlipBox = tbOmSlipInfoEbc.retrieveOmSlipInfo_seq(box);
//
//                            if (!omSlipBox.isEmpty()) {
//                                omSlipInfo.set("slip_seq",omSlipBox.getLong("slip_seq") + 1);
//                            } else {
//                                omSlipInfo.set("slip_seq",1); // 일련번호
//                            }
//
//                            omSlipInfo.set("cancel_dt",""); // 취소일자
//                            omSlipInfo.set("use_yn","Y"); // 사용여부
//                            omSlipInfo.set("cancel_yn","N"); // 취소여부
//
//                            // 등록일시
//                            omSlipInfo.set("reg_dt",TimeUtils.getyyyyMMdd((Date) transReqCc.get("now")) + TimeUtils.getHHmmss((Date) transReqCc.get("now")));
//                            omSlipInfo.set("update_dt",""); // 변경일시
//
//                            // PRODUCT_ID 별 Insert (USE_YN=‘Y’)
//                            tbOmSlipInfoEbc.insertOmSlipInfo(omSlipInfo);
//                            //DB입력실패case : 카드 취소 거래 결과처리 실패 -- 부분취소 매출전표 저장 오류
//                        }
//
//                    }
//                }
//                // 전체취소
//                else {
//
//                    logger.println("◆ STEP:4-11 전체취소 응답클래스 셋팅.");
//                    logger.println("◆ STEP:4-11-1 통합거래원장(tb_pay_info) 취소상태 Update.");
//
//                    LData trans = new LData();
//                    CommonBizCPbc.initializeTrans(trans);
//                    trans.set("tid",transReqCc.getString("tid"));
//                    trans.set("state_cd",stateCd);
//                    trans.set("cc_dt",ccDt);
//                    trans.set("cc_tm",ccTm);
//                    trans.set("url_inform_cd",CommonConstants.TRANS_URL_SND_NONE);
//
//                    if("1".equals(transReqCc.getString("pay_inform_cl").substring(1,2)))
//                        trans.set("cc_url_re_inform_cl", "Y");
//                    else
//                        trans.set("cc_url_re_inform_cl", "N");
//
//                    tbPayInfoEbc.updateTransCancel(trans);
//
//                    logger.println("◆ STEP:4-11-2 거래원장History(tb_trans_history) 취소상태 Update.");
//
//                    LData transHistory = new LData();
//                    CommonBizCPbc.initializeTransHistory(transHistory);
//                    transHistory.set("tid",transReqCc.getString("tid"));
//                    transHistory.set("state_cd",stateCd); //
//                    transHistory.set("cc_status",ccStatus);
//                    transHistory.set("cc_dt",ccDt);
//
//                    tbTransHistoryEbc.updateTransHistoryCancel(transHistory);
//
//                    logger.println("◆ STEP:4-11-3 카드 거래내역(tb_card_pay) 취소상태 Update.");
//
//                    LData cardTrans = new LData();
//                    CommonBizCPbc.initializeCardTrans(cardTrans);
//                    functionsCPbc.copyToCardTrans(transReqCc, cardTrans);
//
//                    cardTrans.set("tid",transReqCc.getString("tid"));
//                    cardTrans.set("state_cd",stateCd);
//                    cardTrans.set("cc_req_dt",transReqCc.getString("cc_req_dt"));
//                    cardTrans.set("cc_req_tm",transReqCc.getString("cc_req_tm"));
//                    cardTrans.set("cc_dt",ccDt);
//                    cardTrans.set("cc_tm",ccTm);
//                    cardTrans.set("cc_reason",transReqCc.getString("cc_reason"));
//                    cardTrans.set("cc_reason_cd",transReqCc.getString("cc_reason_cd"));
//                    cardTrans.set("cc_id",transReqCc.getString("cc_id"));
//                    cardTrans.set("cc_ip",transReqCc.getString("cc_ip"));
//                    cardTrans.set("chk_card_cc_yn",checkRealCc);
//
//                    tbCardPayEbc.updateCardTransCancel(cardTrans);
//                    //DB입력실패case : 카드 취소 거래 결과처리 실패 -- 카드거래내역 상태변경 오류
//
//                    //간편결제관련 추가 2017.02.07 결제내역 전송을 위한 저장
//                    if((StringUtils.equals(transReqCc.getString("mobis_pay_type"), "03") && StringUtils.equals(transReqCc.getString("m_cstmr_id").substring(10, 12), "01"))
//                            || (StringUtils.equals(transReqCc.getString("mobis_pay_type"), "01") || StringUtils.equals(transReqCc.getString("mobis_pay_type"), "02")) ){ //간편결제 && 모비스부품센터 or (오프라인 || 바로결제)
//
//                        LData inputData = new LData();
//                        inputData.set("mid",transReqCc.getString("mid"));
//                        inputData.set("tid",transReqCc.getString("tid"));
//                        LData merchantBox = tbMerchantEbc.retrieveMemberInfo(inputData); // tb_merchant 조회
//
//                        transReqCc.set("m_type", merchantBox.getString("m_type")); // 회원사 Type
//                        transReqCc.set("cncl_yn", "Y"); // 회원사 Type
//                        transReqCc.set("goods_amt",transReqCc.getLong("cancelAmt")*(-1));
//                        transReqCc.set("card_app_no","C"+transReqCc.getString("app_no"));
//                        transReqCc.set("mobis_discount_amt", transReqCc.getString("mobis_discount_amt"));	//취소할인금액
//                        transReqCc.set("req_dt",ccDt);
//                        transReqCc.set("org_collect_dt",transReqCc.getString("app_dt")); //원수금일자R
//
//                        transReqCc.set("co_no", merchantBox.getString("co_no"));
//                        transReqCc.set("co_nm", merchantBox.getString("co_nm"));
//                        transReqCc.set("boss_nm", merchantBox.getString("boss_nm"));
//                        transReqCc.set("co_tel_no", merchantBox.getString("tel_no"));
//                        transReqCc.set("addr_no1", merchantBox.getString("addr_no1"));
//                        transReqCc.set("addr_no2", merchantBox.getString("addr_no2"));
//
//                        //모비스수금집계 테이블 저장
//                        tbMobisEbc.createMobisCollectTotal(transReqCc);
//                        //DB입력실패case : 카드 취소 거래 결과처리 실패 -- 모비스 수금 건별집계 저장 오류
//
//                        //모비스월별소계 테이블에 변경된 월별수금합계금액 반영 2017.02.16
//                        LData input = new LData();
//                        input.setString("mid", transReqCc.getString("transReq"));
//                        input.setString("co_no", transReqCc.getString("m_cstmr_id"));
//                        String update_base_ym = transReqCc.getString("req_dt");
//                        if(StringUtils.isNotEmpty(update_base_ym) && update_base_ym.length() >= 6) {
//                            update_base_ym = update_base_ym.substring(0, 6);
//                        } else {
//                            update_base_ym = TimeUtils.getyyyyMMdd().substring(0, 6);
//                        }
//                        input.setString("update_base_ym", update_base_ym);
//                        //모비스 수금년월별 합계 금액 조회
//                        LMultiData inputList = tbMobisEbc.retrieveMobisCollectMonthInfo(input);
//                        for(int inx = 0 ; inx < inputList.getDataCount(); inx++) {
//                            LData data = inputList.getLData(inx);
//                            //모비스 기준년월별 소계 테이블에 데이터 적재
//                            tbMobisEbc.mergeMobisCollectSubtotal(data);
//                            //DB입력실패case : 카드 취소 거래 결과처리 실패 -- 모비스 수금 월간소계 데이터 저장 오류
//                        }
//                    }
//
//                    // 범용APP case 파라미터 조회
//                    if(StringUtils.equals(transReqCc.getString("service_code"), CommonConstants.SERVICE_CODE_STUDIOBLACK)){
//                        LData inputData = new LData();
//                        inputData.set("mid",transReqCc.getString("mid"));
//                        LData merchantBox = tbMerchantEbc.retrieveMemberInfo(inputData); // tb_merchant 조회
//
//                        transReqCc.set("boss_nm", merchantBox.getString("boss_nm"));
//                        transReqCc.set("co_tel_no", merchantBox.getString("tel_no"));
//                        transReqCc.set("addr_no1", merchantBox.getString("addr_no1"));
//                        transReqCc.set("addr_no2", merchantBox.getString("addr_no2"));
//                    }
//
//
//                    // 매입요청(tb_acqu_req) 테이블 상태,매입요청취소일시 필드 변경
//                    // 2015-08-04
//                    // 기존에 tb_trans_history.status가 00, 10일때의 조건에서...
//                    // '전취소'일 경우에만 tb_acqu_req.status를 2로 update하는걸로 수정
//                    if (isAcquReq) {
//
//                        logger.println("◆ STEP:4-11-4 매입요청(tb_acqu_req) 매입요청상태,매입요청취소일시 Update.");
//                        LData acquReqBox = new LData();
//                        acquReqBox.set("tid", transReqCc.getString("tid"));
//                        acquReqBox.set("status", "2"); // 상태:요청취소(2)
//                        acquReqBox.set("acqu_cc_dt", TimeUtils.getyyyyMMddHHmmss(new Date())); // 매입요청취소일시
//                        tbAcquReqEbc.updateAcquReqResult(acquReqBox);
//                        //DB입력실패case : 카드 취소 거래 결과처리 실패 -- 매입요청 상태변경 오류
//                    }
//
//                    if (transReqCc.getBoolean("promotion_yn")) {
//                        logger.println("◆ STEP:4-11-5 프로모션 거래내역(tb_promotion_trans) 취소상태 Update.");
//
//                        LData promotion = new LData();
//
//                        promotion.set("tid",transReqCc.getString("tid"));
//                        promotion.set("cc_dt",ccDt);
//                        promotion.set("state_cd",stateCd);
//                        promotion.set("promotion_cd",transReqCc.getString("promotion_cd"));
//                        tbPromotionTransEbc.updatePromotionTransCancel(promotion);
//
//                        logger.println("◆ STEP:4-11-6 프로모션 취소 거래 누적 반영.");
//
//                        long accumulateAmt = transReqCc.getLong("accumulate_amt");
//
//                        if (StringUtils.equals(transReqCc.getString("recover_yn"), "Y")) {
//                            accumulateAmt = accumulateAmt - transReqCc.getLong("discount_amt"); // 누적할인금액
//                        }
//
//                        // if ( !
//                        // StringUtils.equals(transReqCc.getPromotion_status(),
//                        // "2") ) // 조건 제거
//                        // {
//                        // 프로모션이 '완료' 상태인 경우 반영 안함 (상태가 중지시에는 반영)
//                        long ccDiscountAmt = transReqCc.getLong("cc_discount_amt") + transReqCc.getLong("discount_amt"); // 취소 누적금액
//
//                        transReqCc.set("accumulate_amt",accumulateAmt);
//                        transReqCc.set("cc_discount_amt",ccDiscountAmt);
//
//                        logger.printf("◆ STEP:4-11-7 프로모션 취소 거래 누적 반영. {%d} / {%d} ", accumulateAmt, ccDiscountAmt);
//
//                        if (StringUtils.equals(transReqCc.getString("recover_yn"), "Y")) // 혜택 복원이 되므로 ACCUMULATE_AMT에서 DISCOUNT_AMT를 뺀다.
//                        {
//                            tbPromotionAccumulateEbc.updatePromotionAccumulateCancel(transReqCc);
//                        } else // 혜택 복원이 안되므로 ACCUMULATE_AMT는 그대로 둔 채 CC_DISCOUNT_AMT만 취소금액 누적
//                        {
//                            tbPromotionAccumulateEbc.updatePromotionAccumulateCancel2(transReqCc);
//                        }
//                        //DB입력실패case : 카드 취소 거래 결과처리 실패 -- 프로모션 취소거래 반영 오류
//
//                        // }
//                    }
//
//                    // 오픈마켓 전체취소 20150312 add by cbd
//                    if (StringUtils.equals(transReqCc.getString("om_trans_yn"), "Y")) {
//                        logger.println("◆ STEP:4-11-8 매출전표 정보 원장(tb_om_slip_info) 취소상태 Update.");
//
//                        LData omSlipInfo = new LData();
//                        CommonBizCPbc.initializeOmSlipInfo(omSlipInfo);
//                        functionsCPbc.copyToOmSlipInfo(transReqCc, omSlipInfo);
//
//                        omSlipInfo.set("use_yn","Y"); // 사용여부
//                        omSlipInfo.set("cancel_yn","Y"); // 취소여부
//                        omSlipInfo.set("cancel_dt",TimeUtils.getyyyyMMdd((Date) transReqCc.get("now"))+ TimeUtils.getHHmmss((Date) transReqCc.get("now")));
//                        omSlipInfo.set("update_dt",omSlipInfo.getString("cancel_dt"));
//                        tbOmSlipInfoEbc.updateOmSlipInfo(omSlipInfo);
//                        //DB입력실패case : 카드 취소 거래 결과처리 실패 -- 매출전표 취소처리 오류
//                    }
//                }
//                // SMS 전송
//                if (StringUtils.isNotEmpty(transReqCc.getString("ord_cp")) && BzUtils.isSMSCode(transReqCc.getString("pay_inform"))) {
//                    logger.println("◆ STEP:4-12 SMS 전송.");
//                    LData message = new LData();
//                    message.set("recvTelNo",transReqCc.getString("ord_cp"));
//                    message.set("sendTelNo","");
//                    message.set("smsMsg",
//                            "결제취소" + System.getProperty("line.separator")
//                                    + StrUtils.cutStr(transReqCc.getString("co_nm"), 80) + System.getProperty("line.separator")
//                                    + StrUtils.cutStr(transReqCc.getString("goods_nm"), 100) + System.getProperty("line.separator")
//                                    + StrUtils.cutStr(StrUtils.getMoneyType(transReqCc.getLong("cancelAmt")), 11) + "원"
//                            // + System.getProperty("line.separator") + "영수증출력www.bluewalnut.co.kr"
//                    );
//                    SmsNotification smsNotification = new SmsNotification();
//                    smsNotification.doNotification(message);
//                } else {
//
//                    logger.println("◆ STEP:4-12 SMS 전송->SKIP.");
//                }
//
//                // Mail 전송
//                if (StringUtils.isNotEmpty(transReqCc.getString("ord_email"))
//                        && BzUtils.isMailCode(transReqCc.getString("pay_inform"))) {
//
//                    logger.println("◆ STEP:4-13 소보법 메일 요청(tb_mail_daily 테이블 저장).");
//                    LData mailDaily = new LData();
//                    functionsCPbc.copyToCcMailDaily(transReqCc, mailDaily);
//                    mailDaily.set("send_cnt",0);
//                    mailDaily.set("state_cd",CommonConstants.MAIL_STATE_CD_1);
//                    mailDaily.set("status",CommonConstants.MAIL_STATUS_0);
//                    mailDaily.set("template_id",CommonConstants.MAIL_TYPE_0002);
//                    mailDaily.set("cc_dt",ccDt);
//                    mailDaily.set("cc_tm",ccTm);
//                    mailDaily.set("fn_cd",transReqCc.getString("fn_cd"));
//                    mailDaily.set("pay_no",transReqCc.getString("card_no"));
//
//                    String payNo = mailDaily.getString("pay_no");
//                    mailDaily.set("pay_no", CryptUtil.doCryption("DataMasking", payNo, "1"));
//                    mailDaily.set("enc_pay_no"  , CryptUtil.doCryption("EncKM", payNo));
//                    mailDaily.set("hmac_pay_no" , CryptUtil.doCryption("EncSHA256", payNo));
//
//                    tbMailDailyEbc.insertMailDaily(mailDaily);
//                    //DB입력실패case : 카드 취소 거래 결과처리 실패 -- 메일전송 정보 저장 오류
//                } else {
//
//                    logger.println("◆ STEP:4-13 소보법 메일 요청(tb_mail_daily 테이블 저장)->SKIP.");
//                }
//
//                logger.println("◆ STEP:4-14 거래취소후 한도 체크 SMS/Mail 전송 처리.");
//
//                postProcessAboutCancelRiskManager(transReqCc);
//                //DB입력실패case : 카드 취소 거래 결과처리 실패 -- 결제취소후 한도누적치 원복 오류
//
//                //오프라인결제 영수증정보 추가 2017.02.06
//                if(StringUtils.equals("Y",transReqCc.getString("off_yn"))){
//
//                    //신용구분 	Gubun
//                    //승인사 	CardName
//                    //거래일자  TransDt
//                    //거래시간  TransTm
//                    //카드번호	CardNo
//                    //할부구분	Quota
//                    //가맹점번호 FnNo
//                    //승인번호	AuthCode
//                    //매입사	AcquCardName
//                    //금액		SupplyAmt
//                    //부가세	VatAmt
//                    //면세금액	TaxFreeAmt
//                    //합계		TotalAmt
//                    //가맹점명	FnMainNm
//                    //하위가맹점명		CoNm
//                    //하위사업자번호	CoNo
//                    //하위대표자명		BossNm
//                    //하위전화번호		TelNo
//                    //하위주소			Address
//                    //CatId
//                    //선불카드 잔액		GiftBalance
//                    result.set("Gubun", "신용취소");
//                    LData input = new LData();
//
//                    input.set("code_cl","0002");
//                    input.set("code1",appCo);
//                    LData codeData = tbCodeEbc.retrieveCode(input);
//                    result.set("CardName", codeData.getString("desc1"));
//
//
//                    result.set("TransDt", ccDt);
//                    result.set("TransTm", ccTm);
//                    String quota = StringUtils.defaultString(transReqCc.getString("instmnt_mon"));
//                    if(StringUtils.equals(StringUtils.defaultString(transReqCc.getString("instmnt_mon")),"00")) quota = "일시불";
//                    if(StringUtils.equals(StringUtils.defaultString(transReqCc.getString("non_interest_cl")),"1")) quota = quota+"(무)";
//                    result.set("CardCl", StringUtils.defaultString(transReqCc.getString("card_cl"),"0"));	//카드구분
//                    result.set("CardNo", transReqCc.getString("card_no"));
//                    result.set("Quota", quota);
//                    result.set("FnNo", transReqCc.getString("fn_no"));
//                    result.set("AuthCode", transReqCc.getString("card_app_no"));
//
//                    input.set("code_cl","0002");
//                    input.set("code1",acquCo);
//                    codeData = tbCodeEbc.retrieveCode(input);
//                    result.set("AcquCardName", codeData.getString("desc1"));
//
//                    //금액
//                    result.set("TotalAmt", transReqCc.getLong("cancelAmt"));
//                    transReqCc.set("supp_amt",StringUtils.defaultIfEmpty(transReqCc.getString("supp_amt"),"0"));
//                    transReqCc.set("goods_vat",StringUtils.defaultIfEmpty(transReqCc.getString("goods_vat"),"0"));
//
//                    //부가세는 DB에서 추출하여 출력. 2018.02.21
//                    result.set("SupplyAmt", transReqCc.getString("supp_amt"));
//                    result.set("VatAmt", transReqCc.getString("goods_vat"));
//                    result.set("TaxFreeAmt", transReqCc.getString("taxfree_amt"));
//                    result.set("PointAmt", transReqCc.getString("point_amt"));
//
//                    transReqCc.set("today", ccDt);
//                    LData fnNmData = tbTerminalEbc.retrieveFnNm(transReqCc);
//                    result.set("FnMainNm", fnNmData.getString("main_nm"));
//
//                    result.set("CoNo", transReqCc.getString("co_no"));
//                    result.set("CoNm", transReqCc.getString("co_nm"));
//                    result.set("BossNm", transReqCc.getString("boss_nm"));
//                    result.set("TelNo", StringUtils.defaultString(transReqCc.getString("co_tel_no")));
//                    result.set("Address", StringUtils.defaultString(transReqCc.getString("addr_no1"))+" "+StringUtils.defaultString(transReqCc.getString("addr_no2")));
//                    result.set("CatId", transReqCc.getString("terminal_no"));
//                    if(StringUtils.equals(transReqCc.getString("card_cl"), "2")){//기프트카드
//                        if(StringUtils.isNotBlank(cancelResult.getBalanceInfo())){
//                            result.set("GiftBalance", cancelResult.getBalanceInfo());
//                        }
//                    }
//
//                    result.set("BuyerName", transReqCc.getString("ord_nm"));
//                }
//            }
//            // 취소 실패
//            else {
//
//                if (sendAble) {
//                    boolean sendMail = false;
//                    switch (cancelResult.getSendMode()) {
//                        case ResultTrans.TRANS_INIT:
//                            result.set("returnCd", "1102");
//                            sendMail = true;
//                            break;
//                        case ResultTrans.TRANS_CONNECT_FAIL:
//                            result.set("returnCd", "1103");
//                            sendMail = true;
//                            break;
//                        case ResultTrans.TRANS_SEND_FAIL:
//                            result.set("returnCd", "1104");
//                            sendMail = true;
//                            break;
//                        case ResultTrans.TRANS_READ_FAIL:
//                            result.set("returnCd", "1105");
//                            sendMail = true;
//                            break;
//                        case ResultTrans.TRANS_AUTHREAD_FAIL:
//                            result.set("returnCd", "1105");
//                            sendMail = true;
//                            break;
//                        case ResultTrans.TRANS_UNKNOWN_FAIL:
//                            result.set("returnCd", "9999");
//                            sendMail = true;
//                            break;
//                        case ResultTrans.TRANS_OK:
//                            if (isTimeout && StringUtils.equals(transReqCc.getString("join_type"), "0")) { // 중계
//                                // 가맹점의 응답코드가 Timeout(카드사 및 Anylink)발생한 경우
//                                result.set("returnCd", "3P09");
//                            } else {
//                                result.set("returnCd", "2003");
//                            }
//                            sendMail = false;
//                            break; // 취소 실패
//                        default:
//                            result.set("returnCd", "9999");
//                            sendMail = true;
//                    }
//                    // 메일전송
//                    if (sendMail) {
//                        StringBuffer mailMsg = new StringBuffer(16);
//                        // 메일 내용 설정
//                        mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 신용카드 취소전문 송수신 오류발생");
//                        mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ TID : ").append(transReqCc.getString("tid"));
//                        mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 취소일자 : ")
//                                .append(TimeUtils.getyyyyMMdd((Date) transReqCc.get("now")));
//                        mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 취소시간 : ")
//                                .append(TimeUtils.getHHmmss((Date) transReqCc.get("now")));
//                        mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 취소금액 : ").append(transReqCc.getLong("cancelAmt"));
//                        mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ EDI_NO : ").append(transReqCc.getInt("sequence"));
//                        mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 취소형태 : ").append(
//                                StringUtils.equals(transReqCc.getString("partialCancelCode"), "1") ? "부분취소" : "전체취소");
//                        mailMsg.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;◆ 취소서버명 : ").append(transReqCc.getString("svr_nm"));
//                        NotiUtils.notify("N004", null, "신용카드 취소전문 송수신 오류발생", mailMsg.toString(),transReqCc.getString("mid"));
//                    }
//                }
//            }
//            ntxManager.nestedCommit();
//        } catch (LException ex) {
//            //DB입력실패 : 카드 취소 거래 결과처리 실패 -- 상세구분 필요!
//            throw ExceptionUtil.createException("PG", "3P15", ex);
//        } catch (Exception ex) {
//            throw ExceptionUtil.createException("PG", "9999", ex);
//        } finally {
//            try {
//                ntxManager.nestedRelease();
//            } catch (Exception eex) {
//                LLog.err.printf("Error: {%s}", eex.getStackTrace().toString());
//            }
//        }
//        try {
//            ntxManager.nestedBegin();
//            // 모든 취소성공,실패 거래내역에 대해서 취소내역 저장
//            logger.println("◆ STEP:4-16 취소내역(tb_cancel_history) 저장.");
//            LData cancelHistory = new LData();
//            if (StringUtils.isBlank(transReqCc.getString("svc_cd")))
//                transReqCc.set("svc_cd",CommonConstants.SVC_CD_CARD); // 지불수단
//            functionsCPbc.copyToCcCancelHistory(transReqCc, cancelHistory);
//            logger.println("◆ STEP:4-16 returnCd." + result.getString("returnCd"));
//            cancelHistory.set("cc_rslt_cd",result.getString("returnCd")); // 취소결과코드
//            cancelHistory.set("cc_rslt",MessageService.getInstance().getMessage(result.getString("returnCd"))); // 취소결과메시지
//            logger.println("cc_rslt_cd::"+result.getString("returnCd")+"cc_rslt:::"+cancelHistory.getString("cc_rslt"));
//			/*if (StringUtils.isBlank(result.getString("resultCode"))) {
//				result.set("resultCode", result.getString("returnCd"));
//				result.set("resultMsg", cancelHistory.getString("cc_rslt"));
//			}*/
//
//            cancelHistory.set("cc_cl",StringUtils.equals(transReqCc.getString("partialCancelCode"), "1") ? "2" : "1"); // 취소구분 (전체취소:1,부분취소:2)
//            cancelHistory.set("result_cd", result.getString("resultCode")); // Agent 결과코드
//            cancelHistory.set("result_msg", result.getString("resultMsg")); // Agent 결과메시지
//            cancelHistory.set("mall_reserved", result.getString("mall_reserved")); // 가맹점 사용영역
//            cancelHistory.set("agent_tid", StringUtils.defaultString(result.getString("agent_tid"), "")); // 가맹점 사용영역
//            cancelHistory.set("can_msg_trace_no", StringUtils.defaultIfEmpty(transReqCc.getString("can_msg_trace_no"), null));
//
//            logger.println("skyfour2 card : " + result.getString("mall_reserved"));
//
//            tbCancelHistoryEbc.insertCancelHistory(cancelHistory);
//            ntxManager.nestedCommit();
//
//
//            if (StringUtils.equals(transReqCc.getString("auth_type"), CommonConstants.VAN_AUTH_TYPE_NAVERPAY_CARD)) {
//                logger.println("◆ 네이버 페이 취소 수행");
//                postProcessNaverpayCancel(transReqCc);
//            }
//
//        } catch (LException ex) {
//            //DB입력실패 : 카드 취소 거래내역 저장 오류
//            throw ExceptionUtil.createException("PG", "3P16", ex);
//        } catch (Exception ex) {
//            throw ExceptionUtil.createException("PG", "9999", ex);
//        }  finally {
//            try {
//                ntxManager.nestedRelease();
//            } catch (Exception eex) {
//                LLog.err.printf("Error: {%s}", eex.getStackTrace().toString());
//            }
//        }
//        result.setString("can_msg_trace_no", StringUtils.defaultString(transReqCc.getString("can_msg_trace_no"),""));
//        result.setString("returnMsg",MessageService.getInstance().getMessage(result.getString("returnCd")));
//        return result;
//    }
//}
