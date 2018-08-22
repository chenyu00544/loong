package com.vcvb.chenyu.shop.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.image.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> list;
    private int width;
    final private int EMPTY_VIEW = 0;
    final private int PROGRESS_VIEW = 99;
    final private int BANNER_VIEW = 1;
    final private int GOODS_VIEW = 3;
    final private int ADS11_VIEW = 11;
    final private int ADS_1_2_VIEW = 12;
    final private int ADS_2_1_VIEW = 21;
    final private int ADS22_VIEW = 22;
    final private int ADS33_VIEW = 33;
    final private int ADS14_VIEW = 14;
    final private int ADS25_VIEW = 25;


    public HomeRecyclerViewAdapter(List<Object> list, int width) {
        this.list = list;
        this.width = width;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == BANNER_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_slide, parent, false);
            return new HomeRecyclerViewAdapter.BannerViewHolder(view);
        } else if (viewType == ADS11_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_list_2, parent, false);
            return new HomeRecyclerViewAdapter.AdsViewHolder_11(view);
        } else if (viewType == ADS_1_2_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_list, parent, false);
            return new HomeRecyclerViewAdapter.AdsViewHolder_1_2(view);
        } else if (viewType == ADS_2_1_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_list_3, parent, false);
            return new HomeRecyclerViewAdapter.AdsViewHolder_2_1(view);
        } else if (viewType == ADS22_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_list_4, parent, false);
            return new HomeRecyclerViewAdapter.AdsViewHolder_22(view);
        } else if (viewType == ADS33_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_list_5, parent, false);
            return new HomeRecyclerViewAdapter.AdsViewHolder_33(view);
        } else if (viewType == ADS14_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_list_1, parent, false);
            return new HomeRecyclerViewAdapter.AdsViewHolder_14(view);
        } else if (viewType == ADS25_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_list_6, parent, false);
            return new HomeRecyclerViewAdapter.AdsViewHolder_25(view);
        } else if (viewType == EMPTY_VIEW) {
            return null;
        } else if (viewType == PROGRESS_VIEW) {
            return null;
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base_use, parent, false);
            return new HomeRecyclerViewAdapter.GoodsViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.size() == 0) {
            return EMPTY_VIEW;
        } else if (list.get(position) == null) {
            return PROGRESS_VIEW;
        } else {
            String string = "";
            for (Object key : ((HashMap) list.get(position)).keySet()) {
                string = (String) key;
            }
            int type = 0;
            switch (string) {
                case "banner":
                    type = BANNER_VIEW;
                    break;
                case "ads_11":
                    type = ADS11_VIEW;
                    break;
                case "ads_1_2":
                    type = ADS_1_2_VIEW;
                    break;
                case "ads_2_1":
                    type = ADS_2_1_VIEW;
                    break;
                case "ads_22":
                    type = ADS22_VIEW;
                    break;
                case "ads_33":
                    type = ADS33_VIEW;
                    break;
                case "ads_14":
                    type = ADS14_VIEW;
                    break;
                case "ads_25":
                    type = ADS25_VIEW;
                    break;
                default:
                    type = GOODS_VIEW;
            }
            return type;
        }
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            ArrayList<HashMap> imageMp = ((ArrayList) ((HashMap) list.get(position)).get("banner"));
            ArrayList<String> imageUrls = new ArrayList<>();
            ArrayList<String> titleList = new ArrayList<>();
            for (int i = 0; i < imageMp.size(); i++) {
                imageUrls.add((String) imageMp.get(i).get("url"));
                titleList.add((String) imageMp.get(i).get("title"));
            }
            bannerViewHolder.banner.setImages(imageUrls);
            //设置轮播图的标题集合
            bannerViewHolder.banner.setBannerTitles(titleList);
            bannerViewHolder.banner.start();
        } else if (holder instanceof AdsViewHolder_11) {
            AdsViewHolder_11 adsViewHolder_11 = (AdsViewHolder_11) holder;
        } else if (holder instanceof AdsViewHolder_14) {
            AdsViewHolder_14 adsViewHolder_14 = (AdsViewHolder_14) holder;
        } else if (holder instanceof AdsViewHolder_22) {
            AdsViewHolder_22 adsViewHolder_22 = (AdsViewHolder_22) holder;
        } else if (holder instanceof AdsViewHolder_33) {
            AdsViewHolder_33 adsViewHolder_33 = (AdsViewHolder_33) holder;
        } else if (holder instanceof AdsViewHolder_25) {
            AdsViewHolder_25 adsViewHolder_25 = (AdsViewHolder_25) holder;
        } else if (holder instanceof AdsViewHolder_2_1) {
            AdsViewHolder_2_1 adsViewHolder_2_1 = (AdsViewHolder_2_1) holder;
        } else if (holder instanceof AdsViewHolder_1_2) {
            AdsViewHolder_1_2 adsViewHolder_1_2 = (AdsViewHolder_1_2) holder;
        } else if (holder instanceof GoodsViewHolder) {
            GoodsViewHolder goodsViewHolder = (GoodsViewHolder) holder;
        }
    }

    class GoodsViewHolder extends RecyclerView.ViewHolder {
        TextView mText;

        GoodsViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.item_tx);
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        Banner banner;

        BannerViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
            //设置内置样式，内含六种特效
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            //设置轮播的动画效果，内含多种特效
            banner.setBannerAnimation(Transformer.Accordion);
            //设置轮播间隔时间
            banner.setDelayTime(5000);
            //设置指示器的位置，小点点，左中右。
            banner.setIndicatorGravity(BannerConfig.CENTER);
            banner.setImageLoader(new GlideImageLoader());
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Log.i("tag", "你点了第" + position + "张轮播图");
                }
            });
        }
    }

    //1-1广告组
    class AdsViewHolder_11 extends RecyclerView.ViewHolder {

        ImageView ads_lsit;

        AdsViewHolder_11(View itemView) {
            super(itemView);
            ads_lsit = itemView.findViewById(R.id.ads_11_1);
            RelativeLayout.LayoutParams ads_11_1_p = (RelativeLayout.LayoutParams) ads_lsit.getLayoutParams();
            ads_11_1_p.width = width;
            ads_11_1_p.height = width / 5;
            ads_lsit.setLayoutParams(ads_11_1_p);
            ads_lsit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("--->>", "1");
                }
            });
        }
    }

    //1-2广告组
    class AdsViewHolder_1_2 extends RecyclerView.ViewHolder {
        ImageView ads_12_1;
        ImageView ads_12_2;
        ImageView ads_12_3;

        AdsViewHolder_1_2(View itemView) {
            super(itemView);
            ads_12_1 = itemView.findViewById(R.id.ads_12_1);
            RelativeLayout.LayoutParams ads_12_1_p = (RelativeLayout.LayoutParams) ads_12_1.getLayoutParams();
            ads_12_1_p.width = width * 150 / 375;
            ads_12_1_p.height = width * 150 / 375 * 10 / 9;
            ads_12_1.setLayoutParams(ads_12_1_p);
            ads_12_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("--->>", "1");
                }
            });
            ads_12_2 = itemView.findViewById(R.id.ads_12_2);
            RelativeLayout.LayoutParams ads_12_2_p = (RelativeLayout.LayoutParams) ads_12_2.getLayoutParams();
            ads_12_2_p.width = width * 225 / 375;
            ads_12_2_p.height = width * 150 / 375 * 5 / 9;
            ads_12_2.setLayoutParams(ads_12_2_p);
            ads_12_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("--->>", "2");
                }
            });
            ads_12_3 = itemView.findViewById(R.id.ads_12_3);
            RelativeLayout.LayoutParams ads_12_3_p = (RelativeLayout.LayoutParams) ads_12_3.getLayoutParams();
            ads_12_3_p.width = width * 225 / 375;
            ads_12_3_p.height = width * 150 / 375 * 5 / 9;
            ads_12_3.setLayoutParams(ads_12_3_p);
            ads_12_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("--->>", "3");
                }
            });
        }
    }

    //2-1广告组
    class AdsViewHolder_2_1 extends RecyclerView.ViewHolder {
        ImageView ads_2_1_1;
        ImageView ads_2_1_2;
        ImageView ads_2_1_3;

        AdsViewHolder_2_1(View itemView) {
            super(itemView);
            ads_2_1_1 = itemView.findViewById(R.id.ads_2_1_1);
            RelativeLayout.LayoutParams ads_2_1_1_p = (RelativeLayout.LayoutParams) ads_2_1_1.getLayoutParams();
            ads_2_1_1_p.width = width * 225 / 375;
            ads_2_1_1_p.height = width * 150 / 375 * 5 / 9;
            ads_2_1_1.setLayoutParams(ads_2_1_1_p);
            ads_2_1_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("--->>", "1");
                }
            });
            ads_2_1_2 = itemView.findViewById(R.id.ads_2_1_2);
            RelativeLayout.LayoutParams ads_2_1_2_p = (RelativeLayout.LayoutParams) ads_2_1_2.getLayoutParams();
            ads_2_1_2_p.width = width * 225 / 375;
            ads_2_1_2_p.height = width * 150 / 375 * 5 / 9;
            ads_2_1_2.setLayoutParams(ads_2_1_2_p);
            ads_2_1_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("--->>", "2");
                }
            });
            ads_2_1_3 = itemView.findViewById(R.id.ads_2_1_3);
            RelativeLayout.LayoutParams ads_2_1_3_p = (RelativeLayout.LayoutParams) ads_2_1_3.getLayoutParams();
            ads_2_1_3_p.width = width * 150 / 375;
            ads_2_1_3_p.height = width * 150 / 375 * 10 / 9;
            ads_2_1_3.setLayoutParams(ads_2_1_3_p);
            ads_2_1_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("--->>", "3");
                }
            });
        }
    }

    //1*4广告组
    class AdsViewHolder_14 extends RecyclerView.ViewHolder {
        ImageView ads_14_1;
        ImageView ads_14_2;
        ImageView ads_14_3;
        ImageView ads_14_4;

        AdsViewHolder_14(View itemView) {
            super(itemView);
            ads_14_1 = itemView.findViewById(R.id.ads_14_1);
            RelativeLayout.LayoutParams ads_14_1_p = (RelativeLayout.LayoutParams) ads_14_1.getLayoutParams();
            ads_14_1_p.width = width / 4;
            ads_14_1_p.height = width / 4 * 8 / 5;
            ads_14_1.setLayoutParams(ads_14_1_p);
            ads_14_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("--->>", "1");
                }
            });
            ads_14_2 = itemView.findViewById(R.id.ads_14_2);
            RelativeLayout.LayoutParams ads_14_2_p = (RelativeLayout.LayoutParams) ads_14_2.getLayoutParams();
            ads_14_2_p.width = width / 4;
            ads_14_2_p.height = width / 4 * 8 / 5;
            ads_14_2.setLayoutParams(ads_14_2_p);
            ads_14_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("--->>", "2");
                }
            });
            ads_14_3 = itemView.findViewById(R.id.ads_14_3);
            RelativeLayout.LayoutParams ads_12_4_p = (RelativeLayout.LayoutParams) ads_14_3.getLayoutParams();
            ads_12_4_p.width = width / 4;
            ads_12_4_p.height = width / 4 * 8 / 5;
            ads_14_3.setLayoutParams(ads_12_4_p);
            ads_14_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("--->>", "3");
                }
            });
            ads_14_4 = itemView.findViewById(R.id.ads_14_4);
            RelativeLayout.LayoutParams ads_14_4_p = (RelativeLayout.LayoutParams) ads_14_4.getLayoutParams();
            ads_14_4_p.width = width / 4;
            ads_14_4_p.height = width / 4 * 8 / 5;
            ads_14_4.setLayoutParams(ads_14_4_p);
            ads_14_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("--->>", "4");
                }
            });
        }
    }

    //2*2广告组
    class AdsViewHolder_22 extends RecyclerView.ViewHolder {
        ImageView ads_22_1;
        ImageView ads_22_2;
        ImageView ads_22_3;
        ImageView ads_22_4;

        AdsViewHolder_22(View itemView) {
            super(itemView);
            ads_22_1 = itemView.findViewById(R.id.ads_22_1);
            RelativeLayout.LayoutParams ads_22_1_p = (RelativeLayout.LayoutParams) ads_22_1.getLayoutParams();
            ads_22_1_p.width = width / 2;
            ads_22_1_p.height = width / 2;
            ads_22_1.setLayoutParams(ads_22_1_p);
            ads_22_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("--->>", "1");
                }
            });
            ads_22_2 = itemView.findViewById(R.id.ads_22_2);
            RelativeLayout.LayoutParams ads_22_2_p = (RelativeLayout.LayoutParams) ads_22_2.getLayoutParams();
            ads_22_2_p.width = width / 2;
            ads_22_2_p.height = width / 2;
            ads_22_2.setLayoutParams(ads_22_2_p);
            ads_22_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("--->>", "1");
                }
            });
            ads_22_3 = itemView.findViewById(R.id.ads_22_3);
            RelativeLayout.LayoutParams ads_22_3_p = (RelativeLayout.LayoutParams) ads_22_3.getLayoutParams();
            ads_22_3_p.width = width / 2;
            ads_22_3_p.height = width / 2;
            ads_22_3.setLayoutParams(ads_22_3_p);
            ads_22_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("--->>", "1");
                }
            });
            ads_22_4 = itemView.findViewById(R.id.ads_22_4);
            RelativeLayout.LayoutParams ads_22_4_p = (RelativeLayout.LayoutParams) ads_22_4.getLayoutParams();
            ads_22_4_p.width = width / 2;
            ads_22_4_p.height = width / 2;
            ads_22_4.setLayoutParams(ads_22_4_p);
            ads_22_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("--->>", "1");
                }
            });
        }
    }

    //3*3广告组
    class AdsViewHolder_33 extends RecyclerView.ViewHolder {
        ImageView ads;

        AdsViewHolder_33(View itemView) {
            super(itemView);
            RelativeLayout.LayoutParams ads_p;
            for (int i = 0; i < 9; i++) {
                switch (i) {
                    case 0:
                        ads = itemView.findViewById(R.id.ads_33_1);
                        break;
                    case 1:
                        ads = itemView.findViewById(R.id.ads_33_2);
                        break;
                    case 2:
                        ads = itemView.findViewById(R.id.ads_33_3);
                        break;
                    case 3:
                        ads = itemView.findViewById(R.id.ads_33_4);
                        break;
                    case 4:
                        ads = itemView.findViewById(R.id.ads_33_5);
                        break;
                    case 5:
                        ads = itemView.findViewById(R.id.ads_33_6);
                        break;
                    case 6:
                        ads = itemView.findViewById(R.id.ads_33_7);
                        break;
                    case 7:
                        ads = itemView.findViewById(R.id.ads_33_8);
                        break;
                    case 8:
                        ads = itemView.findViewById(R.id.ads_33_9);
                        break;
                }
                ads_p = (RelativeLayout.LayoutParams) ads.getLayoutParams();
                ads_p.width = width / 3;
                ads_p.height = width / 3;
                ads.setLayoutParams(ads_p);
                ads.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("--->>", "1");
                    }
                });
            }
        }
    }

    //2*5导航广告组
    class AdsViewHolder_25 extends RecyclerView.ViewHolder {
        ImageView ads;

        AdsViewHolder_25(View itemView) {
            super(itemView);
            RelativeLayout.LayoutParams ads_p;
            for (int i = 0; i < 10; i++) {
                switch (i) {
                    case 0:
                        ads = itemView.findViewById(R.id.ads_25_1);
                        break;
                    case 1:
                        ads = itemView.findViewById(R.id.ads_25_2);
                        break;
                    case 2:
                        ads = itemView.findViewById(R.id.ads_25_3);
                        break;
                    case 3:
                        ads = itemView.findViewById(R.id.ads_25_4);
                        break;
                    case 4:
                        ads = itemView.findViewById(R.id.ads_25_5);
                        break;
                    case 5:
                        ads = itemView.findViewById(R.id.ads_25_6);
                        break;
                    case 6:
                        ads = itemView.findViewById(R.id.ads_25_7);
                        break;
                    case 7:
                        ads = itemView.findViewById(R.id.ads_25_8);
                        break;
                    case 8:
                        ads = itemView.findViewById(R.id.ads_25_9);
                        break;
                    case 9:
                        ads = itemView.findViewById(R.id.ads_25_10);
                        break;
                }
                ads_p = (RelativeLayout.LayoutParams) ads.getLayoutParams();
                ads_p.width = width / 5;
                ads_p.height = width / 5;
                ads.setLayoutParams(ads_p);
                ads.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("--->>", "1");
                    }
                });
            }
        }
    }
}
