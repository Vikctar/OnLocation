package com.vikcandroid.placexpress.comapany_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vikcandroid.placexpress.Profile;
import com.vikcandroid.placexpress.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class BusinessServices extends Fragment {

    // Define an ArrayAdapter that takes Strings
    public ArrayAdapter<String> mBusinessAdapter;
//    private static final int HIGHLIGHT_COLOR = 0x999be6ff;
//
//    private List<ListData> mDataList = Arrays.asList(
//            new ListData("Advertising"),
//            new ListData("Accounting & Fiscality"),
//            new ListData("Brokers"),
//            new ListData("Business Communications"),
//            new ListData("Business Parks"),
//            new ListData("Business Transport"),
//            new ListData("Business Travel Agencies"),
//            new ListData("Consultants"),
//            new ListData("Distributors"),
//            new ListData("Dry Cleaning"),
//            new ListData("Environmental Services"),
//            new ListData("Events and Conferences"),
//            new ListData("Gas & Energy"),
//            new ListData("General Business"),
//            new ListData("General Office Services"),
//            new ListData("Import and Export"),
//            new ListData("Management Consultants"),
//            new ListData("Market Research"),
//            new ListData("Marketing"),
//            new ListData("Money Banking & Insurance"),
//            new ListData("Office Services"),
//            new ListData("Offices and Office Space"),
//            new ListData("Outsourcing"),
//            new ListData("Overseas Business"),
//            new ListData("Printing"),
//            new ListData("Public Relations"),
//            new ListData("Repairs & Maintenance"),
//            new ListData("Retail Services"),
//            new ListData("Safety Consultants"),
//            new ListData("Sales Management"),
//            new ListData("Secretarial Services"),
//            new ListData("Security"),
//            new ListData("Small Businesses"),
//            new ListData("Storage & Warehousing"),
//            new ListData("Trading"),
//            new ListData("Training"),
//            new ListData("Translation Services"),
//            new ListData("Travel & Tours")
//    );
//
//    // declare the color generator and drawable builder
//    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
//    private TextDrawable.IBuilder mDrawableBuilder;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    /*
    public static BusinessServices newInstance(int sectionNumber) {
        BusinessServices fragment = new BusinessServices();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    */

    public BusinessServices() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        //Once the root view for the fragment has been created, it's time to
        // populate the ListView with some data

//        // Create the data for the list view
        String[] businessArray = {
                "Advertising",
                "Business Communications",
                "Events and Conferences",
                "General Office Services", "Office Services",
                "Human Resources", "Import and Export",
                "Management Consultants", "Marketing",
                "Offices and Office Space", "Public Relations",
                "Retail Services", "Sales Management",
                "Training", "Gas & Energy",
                "Business Travel Agencies", "Accounting & Fiscality",
                "Secretarial Services", "Trading",
                "Storage & Warehousing", "Environmental Services",
                "Business Transport", "Money Banking & Insurance",
                "Repairs & Maintenance", "Travel & Tours",
                "Security", "Brokers", "Overseas Business",
                "Distributors", "Printing", "Courier Services",
                "Outsourcing", "Business Parks",
                "Translation Services", "Dry Cleaning", "Small Businesses",
                "Market Research", "General Business", "Consultants",
                "Safety Consultants"


        };

        List<String> businessServices = new ArrayList<>(Arrays.asList(businessArray));

        // Now that we have some data, create an ArrayAdapter.
        // The ArrayAdapter will take some data from a source and use it to
        // populate the ListView it's attached to.
        mBusinessAdapter =
                new ArrayAdapter<>(
                        // The current context (this fragment's parent activity)
                        getActivity(),
                        // ID of the list item to populate
                        R.layout.list_item,
                        // ID of the text view to populate
                        R.id.list_item_textView,
                        // data
                        businessServices
                );

//        mDrawableBuilder = TextDrawable.builder()
//                .round();

        // Get a reference to the list view and attach this adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listView_company);
        listView.setAdapter(mBusinessAdapter);
//        listView.setAdapter(new SampleAdapter(rootView.getContext()));

        // Make the listView items clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String biz = mBusinessAdapter.getItem(position);

                Intent intent = new Intent(getActivity(), Profile.class);//.putExtra(Intent.EXTRA_TEXT, biz);
                intent.putExtra("ID",""+position);
                startActivity(intent);
            }
        });

        return rootView;
    }

//    public class SampleAdapter extends BaseAdapter {
//
//        private LayoutInflater mInflater;
//
//        public SampleAdapter(Context context) {
//            mInflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public int getCount() {
//            return mDataList.size();
//        }
//
//        @Override
//        public ListData getItem(int position) {
//            return mDataList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View view = convertView;
//
//            final ViewHolder holder;
//            if (convertView == null) {
//                convertView = mInflater.inflate(R.layout.biz_list_item, parent, false);
//                holder = new ViewHolder(convertView);
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//
//            ListData item = getItem(position);
//
//            updateCheckedState(holder, item);
//
//            holder.textView.setText(item.data);
//
//            return convertView;
//        }
//
//        private void updateCheckedState(ViewHolder holder, ListData item) {
//            if (item.isChecked) {
//                holder.imageView.setImageDrawable(mDrawableBuilder.build(" ", 0xff616161));
//                holder.view.setBackgroundColor(HIGHLIGHT_COLOR);
////                holder.checkIcon.setVisibility(View.VISIBLE);
//            }
//            else {
//                TextDrawable drawable = mDrawableBuilder.build(String.valueOf(item.data.charAt(0)), mColorGenerator.getColor(item.data));
//                holder.imageView.setImageDrawable(drawable);
//                holder.view.setBackgroundColor(Color.TRANSPARENT);
////                holder.checkIcon.setVisibility(View.GONE);
//            }
//        }
//    }
//
//    private static class ViewHolder {
//
//        private View view;
//
//        private ImageView imageView;
//
//        private TextView textView;
//
//        private ImageView checkIcon;
//
//        private ViewHolder(View view) {
//            this.view = view;
//            imageView = (ImageView) view.findViewById(R.id.biz_imageView);
//            textView = (TextView) view.findViewById(R.id.biz_list_item_textView);
//
//        }
//    }
//
//    private static class ListData {
//
//        private String data;
//
//        private boolean isChecked;
//
//        public ListData(String data) {
//            this.data = data;
//        }
//
////        public void setChecked(boolean isChecked) {
////            this.isChecked = isChecked;
////        }
//    }
}
