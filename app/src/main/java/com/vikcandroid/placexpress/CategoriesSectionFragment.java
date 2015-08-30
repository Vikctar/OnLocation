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
                Intent intent = new Intent(getActivity(), CompanyActivity.class).
                        putExtra(Intent.EXTRA_TEXT, position);
                startActivity(intent);

//                switch (position) {
//                    case 0:
//
//
//                        // Create new fragment and transaction
//                        Fragment bizFragment = new BusinessServices();
//                        FragmentTransaction bizTransaction = getFragmentManager().beginTransaction();
//
//                        // Replace whatever is in the fragment_container view with this fragment,
//                        // and add the transaction to the back stack
//                        bizTransaction.replace(R.id.fragment_container, bizFragment);
//                        bizTransaction.addToBackStack(null);
//
//                        // Commit the transaction
//                        bizTransaction.commit();
//                        break;
//
//                    case 1:
//                        // Create new fragment and transaction
//                        Fragment entFragment = new Entertainment();
//                        FragmentTransaction entTransaction = getFragmentManager().beginTransaction();
//
//                        // Replace whatever is in the fragment_container view with this fragment,
//                        // and add the transaction to the back stack
//                        entTransaction.replace(R.id.fragment_container, entFragment);
//                        entTransaction.addToBackStack(null);
//
//                        // Commit the transaction
//                        entTransaction.commit();
//                        break;
//                }
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
