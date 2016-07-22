package com.vikcandroid.placexpress;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.vikcandroid.placexpress.activities.Accommodation;
import com.vikcandroid.placexpress.activities.Automotive;
import com.vikcandroid.placexpress.activities.Buildings;
import com.vikcandroid.placexpress.activities.BusinessServices;
import com.vikcandroid.placexpress.activities.Computers;
import com.vikcandroid.placexpress.activities.Construction;
import com.vikcandroid.placexpress.activities.Entertainment;
import com.vikcandroid.placexpress.activities.Finance;
import com.vikcandroid.placexpress.activities.FoodAndDrink;
import com.vikcandroid.placexpress.activities.Government;
import com.vikcandroid.placexpress.activities.Health;
import com.vikcandroid.placexpress.activities.Industry;
import com.vikcandroid.placexpress.activities.PersonalServices;
import com.vikcandroid.placexpress.activities.Properties;
import com.vikcandroid.placexpress.activities.PublicServices;
import com.vikcandroid.placexpress.activities.Shopping;
import com.vikcandroid.placexpress.activities.Sports;
import com.vikcandroid.placexpress.activities.Transport;

import java.util.ArrayList;
import java.util.List;

 /*
Fragment representing a section of the app
 */
public class CategoriesSectionFragment extends Fragment {
    //public  static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories_section, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view);
        gridView.setAdapter(new CatAdapter(rootView.getContext()));

        // Add a click listener (OnItemClickListener) to the gridView items
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Intent intent = new Intent(getActivity(), BusinessServices.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(intent);

                        // Create new fragment and transaction

                        break;

                    case 1:
                        Intent entertainment = new Intent(getActivity(), Entertainment.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(entertainment);
                        break;

                    case 2:
                        Intent shopping = new Intent(getActivity(), Shopping.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(shopping);
                        break;

                    case 3:
                        Intent food = new Intent(getActivity(), FoodAndDrink.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(food);
                        break;

                    case 4:
                        Intent buildings = new Intent(getActivity(), Buildings.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(buildings);
                        break;

                    case 5:
                        Intent accommodation = new Intent(getActivity(), Accommodation.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(accommodation);
                        break;

                    case 6:
                        Intent health = new Intent(getActivity(), Health.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(health);
                        break;

                    case 7:
                        Intent finance = new Intent(getActivity(), Finance.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(finance);
                        break;

                    case 8:
                        Intent govt = new Intent(getActivity(), Government.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(govt);
                        break;

                    case 9:
                        Intent properties = new Intent(getActivity(), Properties.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(properties);
                        break;

                    case 10:
                        Intent transport = new Intent(getActivity(), Transport.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(transport);
                        break;

                    case 11:
                        Intent auto = new Intent(getActivity(), Automotive.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(auto);
                        break;

                    case 12:
                        Intent industry = new Intent(getActivity(), Industry.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(industry);
                        break;

                    case 13:
                        Intent publicIntent = new Intent(getActivity(), PublicServices.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(publicIntent);
                        break;

                    case 14:
                        Intent personal = new Intent(getActivity(), PersonalServices.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(personal);
                        break;

                    case 15:
                        Intent sport = new Intent(getActivity(), Sports.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(sport);
                        break;

                    case 16:
                        Intent computers= new Intent(getActivity(), Computers.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(computers);
                        break;

                    case 17:
                        Intent construction = new Intent(getActivity(), Construction.class).
                                putExtra(Intent.EXTRA_TEXT, position);
                        startActivity(construction);
                        break;

                    default:
                        break;
                }
            }
        });




        return rootView;
    }

    private class CatAdapter extends BaseAdapter {
        String[] icon_name = {
                "Business",
                "Entertainment",
                "Shopping",
                "Food/Drink",
                "Buildings",
                "Accommoda...",
                "Health",
                "Finance",
                "Government",
                "Properties",
                "Transport",
                "Automotive",
                "Industry",
                "Public Serv..",
                "Personal Serv..",
                "Sports",
                "Computers",
//                "Contacts",
//                "Deals",
                "Construction"
        };
        private List<CatAdapter.Item> items = new ArrayList<>();
        private LayoutInflater mInflater;

        public CatAdapter(Context context) {
            mInflater = LayoutInflater.from(context);

            items.add(new CatAdapter.Item(R.drawable.ic_business_2));
            items.add(new CatAdapter.Item(R.drawable.ic_entertainment_2));
            items.add(new CatAdapter.Item(R.drawable.ic_shopping_2));
            items.add(new CatAdapter.Item(R.drawable.ic_food_2));
            items.add(new CatAdapter.Item(R.drawable.ic_buildings_2));
            items.add(new CatAdapter.Item(R.drawable.ic_accommodation_2));
            items.add(new CatAdapter.Item(R.drawable.ic_healthcare_2));
            items.add(new CatAdapter.Item(R.drawable.ic_finance_2));
            items.add(new CatAdapter.Item(R.drawable.ic_government_2));
            items.add(new CatAdapter.Item(R.drawable.ic_properties_2));
            items.add(new CatAdapter.Item(R.drawable.ic_transport_2));
            items.add(new CatAdapter.Item(R.drawable.ic_automotive_2));
            items.add(new CatAdapter.Item(R.drawable.ic_industry_2));
            items.add(new CatAdapter.Item(R.drawable.ic_public_2));
            items.add(new CatAdapter.Item(R.drawable.ic_personal_2));
            items.add(new CatAdapter.Item(R.drawable.ic_sports_2));
            items.add(new CatAdapter.Item(R.drawable.ic_computer_2));
//            items.add(new CatAdapter.Item(R.drawable.ic_deals));
//            items.add(new CatAdapter.Item(R.drawable.ic_business_contacts));
            items.add(new CatAdapter.Item(R.drawable.ic_construction_2));

        }




        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return items.get(position).drawableId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            ImageView icon;
            TextView catText;

            if (convertView == null) {
                view = mInflater.inflate(R.layout.grid_view_item, parent, false);
                view.setTag(R.id.icon, view.findViewById(R.id.icon));

            }
            icon = (ImageView)view.getTag(R.id.icon);

            CatAdapter.Item item = (CatAdapter.Item)getItem(position);

            icon.setImageResource(item.drawableId);
            catText = (TextView) view.findViewById(R.id.cat_text);
            catText.setTextSize(2, 14.0F);
            catText.setText(icon_name[position]);

            return view;
        }

        private class Item {
            final int drawableId;

            Item(int drawableId) {
                this.drawableId = drawableId;
            }
        }
    }
}
